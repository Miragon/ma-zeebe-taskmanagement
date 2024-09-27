package io.miragon.zeebe.tm.exporter

import io.camunda.zeebe.exporter.api.context.Context
import io.camunda.zeebe.protocol.record.RecordType
import io.camunda.zeebe.protocol.record.ValueType

class RecordFilter : Context.RecordFilter
{
    /**
     * Accepts only Event records
     */
    override fun acceptType(recordType: RecordType?): Boolean
    {
        return recordType == RecordType.EVENT
    }

    /**
     * Accepts only Job and User Task records
     * A Job record is created when a user task is created, timed out or canceled
     */
    override fun acceptValue(valueType: ValueType?): Boolean
    {
        return valueType == ValueType.USER_TASK || valueType == ValueType.VARIABLE
    }
}