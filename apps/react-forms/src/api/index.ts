import { PlaceOrderDto } from "./generated/model/placeOrderDto.ts";
import { CheckOrderDto } from "./generated/model/checkOrderDto.ts";
import { ItemDto } from "./generated/model/itemDto.ts";

export * from "./generated/model/userTaskDto.ts";
export * from "./generated/model/loadItemsDto.ts";
export * from "./generated/model/checkOrderDto.ts";
export * from "./generated/model/itemDto.ts";
export * from "./generated/model/placeOrderDto.ts";

export type FormData = ItemDto | PlaceOrderDto | CheckOrderDto;
