/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import type { Configuration } from "./configuration.ts";
import type { AxiosInstance, AxiosPromise, RawAxiosRequestConfig } from "axios";
import globalAxios from "axios";
// Some imports not used depending on template conditions
import {
    assertParamExists,
    createRequestFunction,
    serializeDataIfNeeded,
    setSearchParams,
    toPathString,
} from "./common.ts";
import { BaseAPI, RequestArgs, RequiredError } from "./base.ts";
import { CompleteTaskDtoFormData } from "../generated/api/model/completeTaskDtoFormData.ts";
import { LoadStartForm200Response } from "../generated/api/model/loadStartForm200Response.ts";
import { MessageDto } from "../generated/api/model/messageDto.ts";
import { UserTaskDto } from "../generated/api/model/userTaskDto.ts";

/**
 * CompleteTaskControllerApi - axios parameter creator
 * @export
 */
export const CompleteTaskControllerApiAxiosParamCreator = function(configuration?: Configuration) {
    return {
        /**
         *
         * @summary Complete task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        completeTask: async (completeTaskDto: CompleteTaskDtoFormData, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'completeTaskDto' is not null or undefined
            assertParamExists("completeTask", "completeTaskDto", completeTaskDto);
            if (!options.url) {
                throw new RequiredError("url");
            }
            const localVarUrlObj = new URL(options.url);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: "POST", ...baseOptions, ...options };
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


            localVarHeaderParameter["Content-Type"] = "application/json";

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            const headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = { ...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers };
            localVarRequestOptions.data = serializeDataIfNeeded(completeTaskDto, localVarRequestOptions, configuration);

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         *
         * @summary Update task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateTask: async (completeTaskDto: CompleteTaskDtoFormData, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'completeTaskDto' is not null or undefined
            assertParamExists("updateTask", "completeTaskDto", completeTaskDto);
            if (!options.url) {
                throw new RequiredError("url");
            }
            const localVarUrlObj = new URL(options.url);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: "POST", ...baseOptions, ...options };
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


            localVarHeaderParameter["Content-Type"] = "application/json";

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            const headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = { ...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers };
            localVarRequestOptions.data = serializeDataIfNeeded(completeTaskDto, localVarRequestOptions, configuration);

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    };
};

/**
 * CompleteTaskControllerApi - functional programming interface
 * @export
 */
export const CompleteTaskControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = CompleteTaskControllerApiAxiosParamCreator(configuration);
    return {
        /**
         *
         * @summary Complete task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async completeTask(completeTaskDto: CompleteTaskDtoFormData, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<MessageDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.completeTask(completeTaskDto, options);
            return (axios) => createRequestFunction(localVarAxiosArgs, globalAxios)(axios);
        },
        /**
         *
         * @summary Update task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async updateTask(completeTaskDto: CompleteTaskDtoFormData, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<MessageDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.updateTask(completeTaskDto, options);
            return (axios) => createRequestFunction(localVarAxiosArgs, globalAxios)(axios);
        },
    };
};

/**
 * CompleteTaskControllerApi - factory interface
 * @export
 */
export const CompleteTaskControllerApiFactory = function(configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = CompleteTaskControllerApiFp(configuration);
    return {
        /**
         *
         * @summary Complete task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        completeTask(completeTaskDto: CompleteTaskDtoFormData, options?: any): AxiosPromise<MessageDto> {
            return localVarFp.completeTask(completeTaskDto, options).then((request) => request(axios, basePath));
        },
        /**
         *
         * @summary Update task
         * @param {CompleteTaskDtoFormData} completeTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateTask(completeTaskDto: CompleteTaskDtoFormData, options?: any): AxiosPromise<MessageDto> {
            return localVarFp.updateTask(completeTaskDto, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * CompleteTaskControllerApi - object-oriented interface
 * @export
 * @class CompleteTaskControllerApi
 * @extends {BaseAPI}
 */
export class CompleteTaskControllerApi extends BaseAPI {
    /**
     *
     * @summary Complete task
     * @param {CompleteTaskDtoFormData} completeTaskDto
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof CompleteTaskControllerApi
     */
    public completeTask(completeTaskDto: CompleteTaskDtoFormData, options?: RawAxiosRequestConfig) {
        return CompleteTaskControllerApiFp(this.configuration).completeTask(completeTaskDto, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     *
     * @summary Update task
     * @param {CompleteTaskDtoFormData} completeTaskDto
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof CompleteTaskControllerApi
     */
    public updateTask(completeTaskDto: CompleteTaskDtoFormData, options?: RawAxiosRequestConfig) {
        return CompleteTaskControllerApiFp(this.configuration).updateTask(completeTaskDto, options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * LoadTaskControllerApi - axios parameter creator
 * @export
 */
export const LoadTaskControllerApiAxiosParamCreator = function(configuration?: Configuration) {
    return {
        /**
         *
         * @summary Load a task form and it\'s data
         * @param {UserTaskDto} userTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        loadData: async (userTaskDto: UserTaskDto, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'userTaskDto' is not null or undefined
            assertParamExists("loadData", "userTaskDto", userTaskDto);
            if (!options.url) {
                throw new RequiredError("url");
            }
            const localVarUrlObj = new URL(options.url);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: "POST", ...baseOptions, ...options };
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


            localVarHeaderParameter["Content-Type"] = "application/json";

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            const headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = { ...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers };
            localVarRequestOptions.data = serializeDataIfNeeded(userTaskDto, localVarRequestOptions, configuration);

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    };
};

/**
 * LoadTaskControllerApi - functional programming interface
 * @export
 */
export const LoadTaskControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = LoadTaskControllerApiAxiosParamCreator(configuration);
    return {
        /**
         *
         * @summary Load a task form and it\'s data
         * @param {UserTaskDto} userTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async loadData(userTaskDto: UserTaskDto, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<LoadStartForm200Response>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.loadData(userTaskDto, options);
            return (axios) => createRequestFunction(localVarAxiosArgs, globalAxios)(axios);
        },
    };
};

/**
 * LoadTaskControllerApi - factory interface
 * @export
 */
export const LoadTaskControllerApiFactory = function(configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = LoadTaskControllerApiFp(configuration);
    return {
        /**
         *
         * @summary Load a task form and it\'s data
         * @param {UserTaskDto} userTaskDto
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        loadData(userTaskDto: UserTaskDto, options?: any): AxiosPromise<LoadStartForm200Response> {
            return localVarFp.loadData(userTaskDto, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * LoadTaskControllerApi - object-oriented interface
 * @export
 * @class LoadTaskControllerApi
 * @extends {BaseAPI}
 */
export class LoadTaskControllerApi extends BaseAPI {
    /**
     *
     * @summary Load a task form and it\'s data
     * @param {UserTaskDto} userTaskDto
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LoadTaskControllerApi
     */
    public loadData(userTaskDto: UserTaskDto, options?: RawAxiosRequestConfig) {
        return LoadTaskControllerApiFp(this.configuration).loadData(userTaskDto, options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * StartProcessControllerApi - axios parameter creator
 * @export
 */
export const StartProcessControllerApiAxiosParamCreator = function(configuration?: Configuration) {
    return {
        /**
         *
         * @summary Get start form
         * @param {{ [key: string]: any; }} [requestBody]
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        loadForm: async (requestBody?: {
            [key: string]: any;
        }, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            if (!options.url) {
                throw new RequiredError("url");
            }
            const localVarUrlObj = new URL(options.url);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: "POST", ...baseOptions, ...options };
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            localVarHeaderParameter["Content-Type"] = "application/json";

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            const headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = { ...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers };
            localVarRequestOptions.data = serializeDataIfNeeded(requestBody, localVarRequestOptions, configuration);

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         *
         * @summary Start a process
         * @param {object} body
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        startProcess: async (body: object, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'body' is not null or undefined
            assertParamExists("startProcess", "body", body);
            if (!options.url) {
                throw new RequiredError("url");
            }
            const localVarUrlObj = new URL(options.url);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: "POST", ...baseOptions, ...options };
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


            localVarHeaderParameter["Content-Type"] = "application/json";

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            const headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = { ...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers };
            localVarRequestOptions.data = serializeDataIfNeeded(body, localVarRequestOptions, configuration);

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    };
};

/**
 * StartProcessControllerApi - functional programming interface
 * @export
 */
export const StartProcessControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = StartProcessControllerApiAxiosParamCreator(configuration);
    return {
        /**
         *
         * @summary Get start form
         * @param {{ [key: string]: any; }} [requestBody]
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async loadForm(requestBody?: {
            [key: string]: any;
        }, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<LoadStartForm200Response>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.loadForm(requestBody, options);
            return (axios) => createRequestFunction(localVarAxiosArgs, globalAxios)(axios);
        },
        /**
         *
         * @summary Start a process
         * @param {object} body
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async startProcess(body: object, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<MessageDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.startProcess(body, options);
            return (axios) => createRequestFunction(localVarAxiosArgs, globalAxios)(axios);
        },
    };
};

/**
 * StartProcessControllerApi - factory interface
 * @export
 */
export const StartProcessControllerApiFactory = function(configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = StartProcessControllerApiFp(configuration);
    return {
        /**
         *
         * @summary Get start form
         * @param {{ [key: string]: any; }} [requestBody]
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        loadForm(requestBody?: { [key: string]: any; }, options?: any): AxiosPromise<LoadStartForm200Response> {
            return localVarFp.loadForm(requestBody, options).then((request) => request(axios, basePath));
        },
        /**
         *
         * @summary Start a process
         * @param {object} body
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        startProcess(body: object, options?: any): AxiosPromise<MessageDto> {
            return localVarFp.startProcess(body, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * StartProcessControllerApi - object-oriented interface
 * @export
 * @class StartProcessControllerApi
 * @extends {BaseAPI}
 */
export class StartProcessControllerApi extends BaseAPI {
    /**
     *
     * @summary Get start form
     * @param {{ [key: string]: any; }} [requestBody]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof StartProcessControllerApi
     */
    public loadForm(requestBody?: { [key: string]: any }, options?: RawAxiosRequestConfig) {
        return StartProcessControllerApiFp(this.configuration).loadForm(requestBody, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     *
     * @summary Start a process
     * @param {object} body
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof StartProcessControllerApi
     */
    public startProcess(body: object, options?: RawAxiosRequestConfig) {
        return StartProcessControllerApiFp(this.configuration).startProcess(body, options).then((request) => request(this.axios, this.basePath));
    }
}



