package com.adammcneilly.mobileleaguehacking.rest

import com.adammcneilly.mobileleaguehacking.models.Hackathon
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Interface that manages all calls to the service.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
interface MLHService {
    /**
     * Retrieve a list of all hackathons from the JSON file.
     */
    @GET("api/v1/events/{region}")
    fun getHackathons(@Path("region") region: String): Observable<List<Hackathon>>
}