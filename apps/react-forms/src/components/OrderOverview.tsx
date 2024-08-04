import {Checkbox, FormControlLabel, Paper, TableBody, TableCell, TableContainer, TableRow} from "@mui/material";
import {tss} from "tss-react";

import {Order, PersonalInformationProps} from "../domain";
import ItemList from "./ItemList.tsx";

interface OrderOverviewProps {
    order: Order;
    orderId?: string;
    checkable?: boolean;
    className?: string;
}

const useStyles = tss.create({
    root: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        "&>div": {
            margin: "10px"
        }
    },
    h1: {
        fontSize: "2em",
        margin: "10px",
    },
    h2: {
        fontSize: "1.5em",
        margin: 0,
    },
});

const OrderOverview = (props: OrderOverviewProps) => {
    const {order, orderId, checkable, className} = props;

    const {classes, cx} = useStyles();

    const PersonalInformation = (info: PersonalInformationProps) => {
        const name = `${info.firstname} ${info.lastname}`;
        const rows = [
            {label: "Name", value: name},
            {label: "Email", value: info.email},
            {label: "Street", value: info.street},
            {label: "City", value: info.city},
            {label: "Zip", value: info.zip},
        ];

        return (
            <TableContainer component={Paper}>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow
                            key={row.label}
                            sx={{'&:last-child td, &:last-child th': {border: 0}}}
                        >
                            <TableCell component="th" scope="row">
                                {row.label}
                            </TableCell>
                            <TableCell align="left">{row.value}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </TableContainer>
        )

    }

    return (
        <div className={cx(classes.root, className)}>
            <h1 className={classes.h1}>Order Overview</h1>
            {orderId && (
                <div>
                    <h2 className={classes.h2}>Order ID</h2>
                    <p>{orderId}</p>
                </div>
            )}
            <div>
                <h2 className={classes.h2}>Personal Information</h2>
                <PersonalInformation {...order.personalInformation.serialize()}/>
            </div>
            <div>
                <h2 className={classes.h2}>Items</h2>
                <ItemList items={order.items}/>
            </div>
            {checkable && (
                <FormControlLabel control={<Checkbox defaultChecked color="success"/>} label="Is order valid?"/>
            )}
        </div>
    );
}

export default OrderOverview;