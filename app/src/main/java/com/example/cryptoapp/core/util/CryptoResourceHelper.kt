package com.example.cryptoapp.core.util

import com.example.cryptoapp.R

object CryptoResourceHelper {

    fun getIcon(name: String) = when (name.uppercase()) {
        "BTC" -> R.drawable.ic_btc
        "DOGE" -> R.drawable.ic_doge
        "BNB" -> R.drawable.ic_bnb
        "DOT" -> R.drawable.ic_dot
        "DASH" -> R.drawable.ic_dash
        "NEXO" -> R.drawable.ic_nexo
        "AMPL" -> R.drawable.ic_ampl
        else -> R.drawable.ic_dash
    }

    fun getName(name: String) = when (name.uppercase()) {
        "BTC" ->  "BTC"
        "DOGE" -> "DOGE"
        "BNB" ->  "BNB"
        "DOT" ->  "DOT"
        "DASH" -> "DASH"
        "NEXO" -> "NEXO"
        "AMPL" -> "AMPL"
        else -> "BTC"
    }

    fun getFullName(name: String,) = when (name.uppercase()) {
        "BTC" -> "Bitcoin"
        "DOGE" -> "Dogecoin"
        "BNB" -> "Binance Coin"
        "DOT" -> "Polkadot"
        "DASH" -> "Dash"
        "NEXO" -> "Nexo Coin"
        "AMPL" -> "Ampleforth"
        else -> "Btc"
    }
}