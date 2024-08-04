import {PersonalInformation, PersonalInformationProps} from "./PersonalInformation.ts";
import {Item, ItemProps} from "./Item.ts";

interface OrderProps {
    personalInformation: PersonalInformation;
    items: Item[];
}

interface OrderSerialized {
    personalInformation: PersonalInformationProps;
    items: ItemProps[];
}

export class Order {
    private readonly _personalInformation: PersonalInformation;
    private _items: Item[];

    constructor({personalInformation, items}: OrderProps) {
        this._personalInformation = personalInformation;
        this._items = items;
    }

    serialize(): OrderSerialized {
        return {
            personalInformation: this._personalInformation.serialize(),
            items: this._items.map((item) => item.serialize())
        }
    }

    get personalInformation(): PersonalInformation {
        return this._personalInformation;
    }

    get items(): Item[] {
        return this._items;
    }

    set items(items: Item[]) {
        this._items = items;
    }
}