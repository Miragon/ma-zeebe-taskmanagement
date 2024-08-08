import { PersonalInformation } from "./PersonalInformation.ts";
import { Item } from "./Item.ts";
import { Serializable } from "../tasklist.ts";
import { CheckOrderDto, PlaceOrderDto } from "../api";

interface OrderProps {
    personalInformation: PersonalInformation;
    items: Item[];
}

export class Order implements Serializable {
    private readonly _personalInformation: PersonalInformation;

    constructor({ personalInformation, items }: OrderProps) {
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

    serialize(): PlaceOrderDto {
        return {
            firstname: this._personalInformation.firstname,
            lastname: this._personalInformation.lastname,
            email: this._personalInformation.email,
            street: this._personalInformation.street,
            city: this._personalInformation.city,
            zip: this._personalInformation.zip,
            items: this._items.map(item => ({
                id: item.id,
                quantity: item.quantity,
            })),
        };
    }
}

export class OrderChecked extends Order {
    private readonly _isOrderValid: boolean;

    constructor({ isOrderValid, personalInformation, items }: OrderProps & { isOrderValid: boolean }) {
        super({ personalInformation, items });
        this._isOrderValid = isOrderValid;
    }

    get isOrderValid(): boolean {
        return this._isOrderValid;
    }

    serialize(): CheckOrderDto {
        return {
            firstname: super.personalInformation.firstname,
            lastname: super.personalInformation.lastname,
            email: super.personalInformation.email,
            street: super.personalInformation.street,
            city: super.personalInformation.city,
            zip: super.personalInformation.zip,
            items: super.items.map(item => ({
                id: item.id,
                quantity: item.quantity,
            })),
            isOrderValid: this.isOrderValid,
        };
    }
}