package com.mgtvapi.api.model

import com.mgtvapi.Parcelable
import com.mgtvapi.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class MainFeedResponse(
    val ok: Boolean,
    val clips: List<Clip>,
)

@Serializable
@Parcelize
data class Clip(
    val id: String,
    val magazineId: Long,
    val canAccess: Boolean,
    val hasDownload: Boolean,
    val categoryId: Long,
    val time: Long,
    val seqNr: Long,
    val hideSeqNr: Boolean,
    val title: String,
    val projectTitle: String,
    val categoryTitle: String?,
    val image: String,
    val description: String,
    val shortDescription: String? = "",
    val duration: String,
    // val next: Any,
    val chapterSections: List<ChapterSection>,
    val participants: List<Participant>,
    val teaserFile: String?,
) : Parcelable, PlayableClip() {
    override val episodeTitle: String
        get() = title
    override val artworkUrl: String
        get() = image
    override val summary
        get() = if (shortDescription.isNullOrEmpty()) "" else shortDescription
    override val clipID: String
        get() = id
    override val magazineTitle: String
        get() = projectTitle
}

@Serializable
@Parcelize
data class ChapterSection(
    val title: String,
    val chapters: List<Chapter>,
) : Parcelable

@Serializable
@Parcelize
data class Chapter(
    val title: String,
    val start: Long,
    val end: Long,
) : Parcelable

@Serializable
@Parcelize
data class Participant(
    val id: String,
    val name: String,
    val img: String,
) : Parcelable
