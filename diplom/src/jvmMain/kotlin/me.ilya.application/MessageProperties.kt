package me.ilya.application


import java.util.Date

data class MessageProperties(val text:String, val date:Date, val socialNetwork: SocialNetwork, val tag:String){
    lateinit var tonality: Tonality
}