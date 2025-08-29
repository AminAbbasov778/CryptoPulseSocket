package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.model.CryptoUpdate
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.json.JSONArray
import javax.inject.Inject

class SocketHandler @Inject constructor(private val socket: Socket) {

    private val gson = Gson()


    private val _cryptoListUpdates = MutableSharedFlow<Result<List<CryptoUpdate>>>(replay = 1)
    val cryptoListUpdates = _cryptoListUpdates.asSharedFlow()

    private val _cryptoDetailUpdates = MutableSharedFlow<Result<CryptoUpdate>>(replay = 1)
    val cryptoDetailUpdates = _cryptoDetailUpdates.asSharedFlow()

    init {
        socket.connect()
        observeSocket()
    }

    private fun observeSocket() {
        socket.on(Socket.EVENT_CONNECT) { }

        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            val error = args.firstOrNull() as? Exception

        }

        socket.on("cryptoUpdate") { args ->
            val json = args.firstOrNull()?.toString() ?: return@on
            try {
                val update = gson.fromJson(json, CryptoUpdate::class.java)
                _cryptoListUpdates.tryEmit(Result.success(listOf(update)))
            } catch (e: Exception) {
                _cryptoListUpdates.tryEmit(Result.failure(e))
            }
        }

        socket.on("cryptoDetailUpdate") { args ->
            val json = args.firstOrNull()?.toString() ?: return@on
            try {
                val update = gson.fromJson(json, CryptoUpdate::class.java)
                _cryptoDetailUpdates.tryEmit(Result.success(update))
            } catch (e: Exception) {
                _cryptoDetailUpdates.tryEmit(Result.failure(e))
            }
        }
    }

    fun subscribeCryptoList(coinsList: List<String>) {
        val coinArray = JSONArray(coinsList)
        socket.emit("subscribeCryptoList", coinArray)
    }

    fun subscribeDetail(coinName: String) {
        socket.emit("subscribeDetail", coinName)
    }

    fun emitMessage(message: String) {
        socket.emit("message", message)
    }
}
