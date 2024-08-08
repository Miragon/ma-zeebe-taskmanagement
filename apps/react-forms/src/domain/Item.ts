import { Serializable } from "../tasklist.ts";
import { ItemDto } from "../api";

export class Item implements Serializable {
    private readonly _id: string;
    private readonly _name?: string;
    private readonly _price?: number;
    private readonly _image?: string;

    constructor({ id, name, price, image, quantity }: ItemDto) {
        this._id = id;
        this._name = name;
        this._price = price;
        this._image = image;
        this._quantity = quantity;
    }

    private _quantity?: number;

    get quantity(): number {
        if (!this._quantity) {
            throw new Error("Quantity is not set");
        }
        return this._quantity;
    }

    set quantity(quantity: number) {
        this._quantity = quantity;
    }

    get id(): string {
        return this._id;
    }

    get name(): string {
        if (!this._name) {
            throw new Error("Name is not set");
        }
        return this._name;
    }

    get price(): string {
        if (!this._price) {
            throw new Error("Price is not set");
        }
        return `${this._price} €`;
    }

    get image(): string {
        if (!this._image) {
            throw new Error("Image is not set");
        }
        return this._image;
    }

    serialize(): ItemDto {
        return {
            id: this._id,
            name: this._name,
            price: this._price,
            image: this._image,
            quantity: this._quantity,
        };
    }
}