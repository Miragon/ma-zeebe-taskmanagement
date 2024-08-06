import { Serializable } from "../tasklist.ts";

export interface ItemProps {
    id: string;
    name: string;
    price: number;
    image: string;
}

export class Item implements Serializable {
    private readonly _id: string;
    private readonly _name: string;
    private readonly _price: number;
    private readonly _image: string;

    constructor({ id, name, price, image }: ItemProps) {
        this._id = id;
        this._name = name;
        this._price = price;
        this._image = image;
    }

    private _quantity: number = 0;

    get quantity(): number {
        return this._quantity;
    }

    set quantity(quantity: number) {
        this._quantity = quantity;
    }

    get id(): string {
        return this._id;
    }

    get name(): string {
        return this._name;
    }

    get price(): string {
        return `${this._price} €`;
    }

    get image(): string {
        return this._image;
    }

    serialize(): string {
        return JSON.stringify({
            id: this._id,
            name: this._name,
            price: this._price,
            image: this._image,
            quantity: this._quantity,
        });
    }
}