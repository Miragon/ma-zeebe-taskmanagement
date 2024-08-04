import {Item} from "../domain";
import {tss} from "tss-react/mui";
import Button from "@mui/material/Button";
import {Divider} from "@mui/material";

interface ItemListProps {
    items: Item[];
    addButtonClicked?: (item: Item) => void;
    className?: string;
}

const useStyles = tss.create({
    list: {
        listStyleType: "none",
        padding: 0,
        margin: 0
    },
    item: {
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        padding: "10px",
        "&>:first-of-type": {
            paddingRight: "10px"
        }
    },
    info: {
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start",
        "&>p": {
            margin: 0
        }
    },
    addButton: {
        marginLeft: "10px",
        padding: "5px",
    },
    imageContainer: {
        width: "150px",
        height: "150px"
    },
    image: {
        width: "100%",
        height: "100%",
        objectFit: "contain",
    }
});

const ItemList = (props: ItemListProps) => {
    const {items, className, addButtonClicked} = props;

    const {classes, cx} = useStyles();

    const handleClick = (item: Item) => {
        if (addButtonClicked) {
            addButtonClicked(item);
        }
    }

    return (
        <ul className={cx(classes.list, className)}>
            {items.map((item, index) => (
                <div>
                    <li className={classes.item} key={item.id}>
                        <div className={classes.imageContainer}>
                            <img className={classes.image} src={item.image} alt={item.image}/>
                        </div>
                        <div className={classes.info}>
                            <p>Name: {item.name}</p>
                            <p>Price: {item.price}</p>
                        </div>
                        {addButtonClicked &&
                            <Button className={classes.addButton} onClick={() => handleClick(item)}>Add</Button>}
                    </li>
                    {index < items.length - 1 && <Divider/>}
                </div>
            ))}
        </ul>
    )
}

export default ItemList;