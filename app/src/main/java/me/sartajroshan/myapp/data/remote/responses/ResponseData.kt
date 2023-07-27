package me.sartajroshan.myapp.data.remote.responses


import com.google.gson.annotations.SerializedName

data class ResponseData(
    val invites: Invites = Invites(),
    val likes: Likes = Likes()
) {
    data class Invites(
        val profiles: List<Profile> = listOf(),
        val totalPages: Int = 0,
        @SerializedName("pending_invitations_count")
        val pendingInvitationsCount: Int = 0
    ) {
        data class Profile(
            @SerializedName("general_information")
            val generalInformation: GeneralInformation = GeneralInformation(),
            @SerializedName("approved_time")
            val approvedTime: Double = 0.0,
            @SerializedName("disapproved_time")
            val disapprovedTime: Double = 0.0,
            val photos: List<Photo> = listOf(),
            @SerializedName("last_seen_window")
            val lastSeenWindow: String = "",
            @SerializedName("verification_status")
            val verificationStatus: String = "",
            @SerializedName("has_active_subscription")
            val hasActiveSubscription: Boolean = false,
            @SerializedName("show_concierge_badge")
            val showConciergeBadge: Boolean = false,
            val lat: Double = 0.0,
            val lng: Double = 0.0,
            @SerializedName("last_seen")
            val lastSeen: Any? = null,
            @SerializedName("online_code")
            val onlineCode: Int = 0,
        ) {
            data class GeneralInformation(
                val location: Location = Location(),
                @SerializedName("first_name")
                val firstName: String = "",
                val gender: String = "",
                val age: Int = 0
            ) {
                data class Location(
                    val summary: String = "",
                    val full: String = ""
                )
            }

            data class Photo(
                val photo: String = "",
                @SerializedName("photo_id")
                val photoId: Int = 0,
                val selected: Boolean = false,
                val status: String? = null
            )

        }
    }

    data class Likes(
        val profiles: List<ProfileLikes> = listOf(),
        @SerializedName("can_see_profile")
        val canSeeProfile: Boolean = false,
        @SerializedName("likes_received_count")
        val likesReceivedCount: Int = 0
    ) {
        data class ProfileLikes(
            @SerializedName("first_name")
            val firstName: String = "",
            val avatar: String = ""
        )
    }
}