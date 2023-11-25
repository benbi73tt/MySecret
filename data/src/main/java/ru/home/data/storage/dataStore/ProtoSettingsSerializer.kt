package ru.home.data.storage.dataStore

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import ru.home.data.ProtoSettings
import java.io.InputStream
import java.io.OutputStream

object ProtoSettingsSerializer : Serializer<ProtoSettings> {
    override val defaultValue: ProtoSettings
        get() = ProtoSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoSettings =
        try {
            ProtoSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            exception.printStackTrace()
            defaultValue
        }

    override suspend fun writeTo(t: ProtoSettings, output: OutputStream) {
        t.writeTo(output)
    }
}