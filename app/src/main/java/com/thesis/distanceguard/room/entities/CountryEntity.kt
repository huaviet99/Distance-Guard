package com.thesis.distanceguard.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val countryId: Int = 0,

    @field:Json(name = "country")
    var country: String? = null,

    @field:Json(name = "active")
    var active: Int? = null,

    @field:Json(name = "activePerOneMillion")
    var activePerOneMillion: Double? = null,

    @field:Json(name = "cases")
    var cases: Int? = null,

    @field:Json(name = "casesPerOneMillion")
    var casesPerOneMillion: Double? = null,

    @field:Json(name = "continent")
    var continent: String? = null,

    @Embedded
    @field:Json(name = "countryInfoEntity")
    var countryInfoEntity: CountryInfoEntity? = null,

    @field:Json(name = "critical")
    var critical: Int? = null,

    @field:Json(name = "criticalPerOneMillion")
    var criticalPerOneMillion: Double? = null,

    @field:Json(name = "deaths")
    var deaths: Int? = null,

    @field:Json(name = "deathsPerOneMillion")
    var deathsPerOneMillion: Double? = null,

    @field:Json(name = "oneCasePerPeople")
    var oneCasePerPeople: Int? = null,

    @field:Json(name = "oneDeathPerPeople")
    var oneDeathPerPeople: Int? = null,

    @field:Json(name = "oneTestPerPeople")
    var oneTestPerPeople: Int? = null,

    @field:Json(name = "population")
    var population: Int? = null,

    @field:Json(name = "recovered")
    var recovered: Int? = null,

    @field:Json(name = "recoveredPerOneMillion")
    var recoveredPerOneMillion: Double? = null,

    @field:Json(name = "tests")
    var tests: Int? = null,

    @field:Json(name = "testsPerOneMillion")
    var testsPerOneMillion: Int? = null,

    @field:Json(name = "todayCases")
    var todayCases: Int? = null,

    @field:Json(name = "todayDeaths")
    var todayDeaths: Int? = null,

    @field:Json(name = "todayRecovered")
    var todayRecovered: Int? = null,

    @field:Json(name = "updated")
    var updated: Long? = null
)