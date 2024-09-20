package com.example.jobsandbookmarkstest.Model

import com.google.gson.annotations.SerializedName


data class JobsModelResponse(
    val results: List<JobsModelResponseData>,
)

data class JobsModelResponseData(
    val id: Long?,
    val title: String?,
    val type: Long,
    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetails?,
    @SerializedName("fee_details")
    val feeDetails: FeeDetails?,
    @SerializedName("job_tags")
    val jobTags: List<JobTag>?,
    @SerializedName("job_type")
    val jobType: Long?,
    @SerializedName("job_category_id")
    val jobCategoryId: Long?,
    val qualification: Long?,
    val experience: Long?,
    @SerializedName("shift_timing")
    val shiftTiming: Long?,
    @SerializedName("job_role_id")
    val jobRoleId: Long?,
    @SerializedName("salary_max")
    val salaryMax: Long?,
    @SerializedName("salary_min")
    val salaryMin: Long?,
    @SerializedName("city_location")
    val cityLocation: String?,
    val locality: Long?,
    @SerializedName("premium_till")
    val premiumTill: String?,
    val content: String?,
    @SerializedName("company_name")
    val companyName: String?,
    val advertiser: Long?,
    @SerializedName("button_text")
    val buttonText: String?,
    @SerializedName("custom_link")
    val customLink: String?,
    val amount: String?,
    val views: Long?,
    val shares: Long?,
    @SerializedName("fb_shares")
    val fbShares: Long?,
    @SerializedName("is_bookmarked")
    val isBookmarked: Boolean?,
    @SerializedName("is_applied")
    val isApplied: Boolean?,
    @SerializedName("is_owner")
    val isOwner: Boolean?,
    @SerializedName("updated_on")
    val updatedOn: String?,
    @SerializedName("whatsapp_no")
    val whatsappNo: String?,
    @SerializedName("contact_preference")
    val contactPreference: ContactPreference?,
    @SerializedName("created_on")
    val createdOn: String?,
    @SerializedName("is_premium")
    val isPremium: Boolean?,
    val creatives: List<Crea>,
    val videos: List<Any?>?,
    val locations: List<Location>?,
    val tags: List<Any?>?,
    val contentV3: ContentV3?,
    val status: Long?,
    @SerializedName("expire_on")
    val expireOn: String?,
    @SerializedName("job_hours")
    val jobHours: String?,
    @SerializedName("openings_count")
    val openingsCount: Long?,
    @SerializedName("job_role")
    val jobRole: String?,
    @SerializedName("other_details")
    val otherDetails: String?,
    @SerializedName("job_category")
    val jobCategory: String?,
    @SerializedName("num_applications")
    val numApplications: Long?,
    @SerializedName("enable_lead_collection")
    val enableLeadCollection: Boolean?,
    @SerializedName("is_job_seeker_profile_mandatory")
    val isJobSeekerProfileMandatory: Boolean?,
    @SerializedName("translated_content")
    val translatedContent: Map<String, Any>?,
    @SerializedName("job_location_slug")
    val jobLocationSlug: String?,
    @SerializedName("fees_text")
    val feesText: String?,
    @SerializedName("question_bank_id")
    val questionBankId: Any?,
    @SerializedName("screening_retry")
    val screeningRetry: Any?,
    @SerializedName("should_show_last_contacted")
    val shouldShowLastContacted: Boolean?,
    @SerializedName("fees_charged")
    val feesCharged: Long?,
)

data class PrimaryDetails(
    @SerializedName("Place")
    val place: String,
    @SerializedName("Salary")
    val salary: String,
    @SerializedName("Job_Type")
    val jobType: String,
    @SerializedName("Experience")
    val experience: String,
    @SerializedName("Fees_Charged")
    val feesCharged: String,
    @SerializedName("Qualification")
    val qualification: String,
)

data class FeeDetails(
    @SerializedName("V3")
    val v3: List<Any?>,
)

data class JobTag(
    val value: String,
    @SerializedName("bg_color")
    val bgColor: String,
    @SerializedName("text_color")
    val textColor: String,
)

data class ContactPreference(
    val preference: Long,
    @SerializedName("whatsapp_link")
    val whatsappLink: String,
    @SerializedName("preferred_call_start_time")
    val preferredCallStartTime: String,
    @SerializedName("preferred_call_end_time")
    val preferredCallEndTime: String,
)

data class Crea(
    val file: String?,
    @SerializedName("thumb_url")
    val thumbUrl: String?,
    @SerializedName("creative_type")
    val creativeType: Long?,
    @SerializedName("order_id")
    val orderId: Long?,
    @SerializedName("image_url")
    val imageUrl: String?,
)

data class Location(
    val id: Long,
    val locale: String,
    val state: Long,
)

data class ContentV3(
    @SerializedName("V3")
    val v3: List<V3>,
)

data class V3(
    @SerializedName("field_key")
    val fieldKey: String,
    @SerializedName("field_name")
    val fieldName: String,
    @SerializedName("field_value")
    val fieldValue: String,
)

