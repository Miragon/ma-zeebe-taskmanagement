import {FormEvent, useCallback, useMemo, useState} from "react";
import {postMessage, TasklistEventType} from "../tasklist.ts";
import {Invoice, InvoiceProps} from "../domain/Invoice.ts";

function InvoiceForm(props: InvoiceProps) {
    const [invoice, setInvoice] = useState<Invoice>(new Invoice(props));
    const [latestInvoice, setLatestInvoice] = useState<Invoice>(new Invoice(props));

    const handleChange = useCallback((event: any) => {
        if (!event.target.value) {
            return;
        }

        setInvoice(invoice.partialCopy({
            [event.target.name]: event.target.value
        }));
    }, [invoice]);

    const handleUpdate = useCallback(() => {
        setLatestInvoice(invoice);
        postMessage({
            type: TasklistEventType.UPDATE_EVENT,
            data: invoice.serialize()
        })
    }, [invoice]);

    const handleSubmit = useCallback((event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setLatestInvoice(invoice);
        postMessage({
            type: TasklistEventType.SUBMIT_EVENT,
            data: invoice.serialize()
        });
    }, [invoice]);

    const checkForChanges = useMemo(() => {
        return latestInvoice.equals(invoice);
    }, [invoice, latestInvoice]);

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label>Id
                    <input type="text" name="id" value={invoice.id} onChange={handleChange}/>
                </label>
                <label>Date
                    <input type="date" name="date" value={invoice.date} onChange={handleChange}/>
                </label>
                <label>Amount
                    <input type="number" name="amount" value={invoice.amount} onChange={handleChange}/>
                </label>
                <button type="submit">Submit</button>
                <button type="button" onClick={handleUpdate} disabled={checkForChanges}>Update</button>
            </form>
        </>
    )
}

export default InvoiceForm