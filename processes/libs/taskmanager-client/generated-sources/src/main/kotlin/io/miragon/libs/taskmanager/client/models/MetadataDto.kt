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

package io.miragon.libs.taskmanager.client.models

import io.miragon.libs.taskmanager.client.models.ProcessApplication

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 *
 * @param processApplications 
 */


data class MetadataDto (

    @field:JsonProperty("processApplications")
    val processApplications: kotlin.collections.List<ProcessApplication>

)

