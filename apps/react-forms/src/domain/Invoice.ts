export interface InvoiceProps {
    id: string;
    date: string;
    amount: number;
}

export class Invoice {
    private readonly _id: string;
    private readonly _date: string;
    private readonly _amount: number;

    constructor({id, date, amount}: InvoiceProps) {
        this._id = id;
        this._date = date;
        this._amount = amount;
    }

    partialCopy(data: Partial<InvoiceProps>): Invoice {
        console.log("partialCopy", data);
        return new Invoice({
            id: data.id ?? this._id,
            date: data.date ?? this._date,
            amount: data.amount ?? this._amount
        })
    }

    equals(invoice: Invoice): boolean {
        return this._id === invoice.id
            && this._date === invoice.date
            && this._amount === invoice.amount
    }

    serialize(): InvoiceProps {
        return {
            id: this._id,
            date: this._date,
            amount: this._amount
        }
    }

    get id(): string {
        return this._id;
    }

    get date(): string {
        return this._date;
    }

    get amount(): number {
        return this._amount;
    }
}