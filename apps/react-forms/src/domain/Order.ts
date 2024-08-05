import {PersonalInformation} from "./PersonalInformation.ts";
import {Item} from "./Item.ts";
import {Serializable} from "../tasklist.ts";

interface OrderProps {
    personalInformation: PersonalInformation;
    items: Item[];
}

export class Order implements Serializable {
    private readonly _personalInformation: PersonalInformation;

    constructor({personalInformation, items}: OrderProps) {
        this._personalInformation = personalInformation;
        this._items = items;
    }

    private _items: Item[];

    get items(): Item[] {
        return this._items;
    }

    set items(items: Item[]) {
        this._items = items;
    }

    get personalInformation(): PersonalInformation {
        return this._personalInformation;
    }

    serialize(): string {
        return JSON.stringify({
            personalInformation: JSON.parse(this._personalInformation.serialize()),
            items: this._items.map((item) => JSON.parse(item.serialize())),
        });
    }
}

export class OrderChecked extends Order {
    private readonly _isOrderValid: boolean;

    constructor({isOrderValid, personalInformation, items}: OrderProps & { isOrderValid: boolean }) {
        super({personalInformation, items});
        this._isOrderValid = isOrderValid;
    }

    get isOrderValid(): boolean {
        return this._isOrderValid;
    }

    serialize(): string {
        return JSON.stringify({
            isOrderValid: this._isOrderValid,
            ...JSON.parse(super.serialize())
        });
    }
}