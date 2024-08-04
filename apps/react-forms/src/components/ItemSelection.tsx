import {forwardRef, useImperativeHandle, useState} from "react";
import {Item, ItemProps} from "../domain";
import {tss} from "tss-react/mui";
import ItemList from "./ItemList.tsx";

interface ItemSelectionProps {
    itemsProp: ItemProps[];
    className?: string;
}

export interface ItemSelectionRef {
    getSelectedItems(): Item[];
}

const useStyles = tss.create({
    root: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
    },
});

const ItemSelection = forwardRef<ItemSelectionRef, ItemSelectionProps>((props, ref) => {
    const {itemsProp, className} = props;

    const [items] = useState<Item[]>(itemsProp.map((item) => new Item(item)));
    const [cart, setCart] = useState<Item[]>([]);

    const {classes, cx} = useStyles();

    const getSelectedItems = (): Item[] => {
        return cart;
    }

    useImperativeHandle(ref, () => ({
        getSelectedItems: getSelectedItems,
    }));

    const addToCart = (item: Item) => {
        console.debug("Add to cart", item);
        setCart([...cart, item]);
    }

    return (
        <div className={cx(classes.root, className)}>
            <ItemList items={items} addButtonClicked={addToCart}/>
        </div>
    )
});

export default ItemSelection;