/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package io.miragon.libs.taskmanager.client.apis

import com.fasterxml.jackson.annotation.JsonProperty

import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.ResponseEntity
import org.springframework.http.MediaType


import io.miragon.libs.taskmanager.client.models.CompleteTaskCommand
import io.miragon.libs.taskmanager.client.infrastructure.*

class CompleteTaskControllerApi(client: RestClient) : ApiClient(client) {

    constructor(baseUrl: String) : this(RestClient.builder()
        .baseUrl(baseUrl)
        .messageConverters { it.add(MappingJackson2HttpMessageConverter()) }
        .build()
    )


    @Throws(RestClientResponseException::class)
    fun completeTask(completeTaskCommand: CompleteTaskCommand): kotlin.Boolean {
        val result = completeTaskWithHttpInfo(completeTaskCommand = completeTaskCommand)
        return result.body!!
    }

    @Throws(RestClientResponseException::class)
    fun completeTaskWithHttpInfo(completeTaskCommand: CompleteTaskCommand): ResponseEntity<kotlin.Boolean> {
        val localVariableConfig = completeTaskRequestConfig(completeTaskCommand = completeTaskCommand)
        return request<CompleteTaskCommand, kotlin.Boolean>(
            localVariableConfig
        )
    }

    fun completeTaskRequestConfig(completeTaskCommand: CompleteTaskCommand) : RequestConfig<CompleteTaskCommand> {
        val localVariableBody = completeTaskCommand
        val localVariableQuery = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "*/*"

        val params = mutableMapOf<String, Any>(
        )

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/rest/task/complete",
            params = params,
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }

}
