package com.busrayasar.itunessearchapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result (
        val artistId: Int,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl30: String,
        val artworkUrl60: String,
        val collectionArtistId: Int,
        val collectionArtistName: String,
        val collectionArtistViewUrl: String,
        val collectionCensoredName: String,
        val collectionExplicitness: String,
        val collectionHdPrice: Double,
        val collectionId: Int,
        val collectionName: String,
        val collectionPrice: Double,
        val collectionViewUrl: String,
        val contentAdvisoryRating: String,
        val country: String,
        val currency: String,
        val discCount: Int,
        val discNumber: Int,
        val hasITunesExtras: Boolean,
        val isStreamable: Boolean,
        val kind: String,
        val longDescription: String,
        val previewUrl: String,
        val primaryGenreName: String,
        val releaseDate: String,
        val shortDescription: String,
        val trackCensoredName: String,
        val trackCount: Int,
        val trackExplicitness: String,
        val trackHdPrice: Double,
        val trackHdRentalPrice: Double,
        val trackId: Int,
        val trackName: String,
        val trackNumber: Int,
        val trackPrice: Double,
        val trackRentalPrice: Double,
        val trackTimeMillis: Int,
        val trackViewUrl: String,
        val wrapperType: String
        ): Parcelable


/*
{
 "resultCount":25,
 "results": [
{"wrapperType":"track", "kind":"song", "artistId":909253, "collectionId":1469577723, "trackId":1469577741,
"artistName":"Jack Johnson",
"collectionName":"Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George",
 "trackName":"Upside Down",
  "collectionCensoredName":"Jack Johnson and Friends: Sing-A-Longs and Lullabies for the Film Curious George",
  "trackCensoredName":"Upside Down",
   "artistViewUrl":"https://music.apple.com/us/artist/jack-johnson/909253?uo=4",
   "collectionViewUrl":"https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4",
   "trackViewUrl":"https://music.apple.com/us/album/upside-down/1469577723?i=1469577741&uo=4",
"previewUrl":"https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/5e/5b/3d/5e5b3df4-deb5-da78-5d64-fe51d8404d5c/mzaf_13341178261601361485.plus.aac.p.m4a",
"artworkUrl30":"https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/08/11/d2/0811d2b3-b4d5-dc22-1107-3625511844b5/00602537869770.rgb.jpg/30x30bb.jpg",
"artworkUrl60":"https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/08/11/d2/0811d2b3-b4d5-dc22-1107-3625511844b5/00602537869770.rgb.jpg/60x60bb.jpg",
 "artworkUrl100":"https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/08/11/d2/0811d2b3-b4d5-dc22-1107-3625511844b5/00602537869770.rgb.jpg/100x100bb.jpg",
  "collectionPrice":9.99, "trackPrice":1.29, "releaseDate":"2005-01-01T12:00:00Z",
   "collectionExplicitness":"notExplicit", "trackExplicitness":"notExplicit", "discCount":1,
   "discNumber":1, "trackCount":14, "trackNumber":1, "trackTimeMillis":208643, "country":"USA",
   "currency":"USD", "primaryGenreName":"Rock", "isStreamable":true},

//Dönen listedeki bir item
* */