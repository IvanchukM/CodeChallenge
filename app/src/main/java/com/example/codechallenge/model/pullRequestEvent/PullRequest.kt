package com.example.codechallenge.model.pullRequestEvent

import com.google.gson.annotations.SerializedName

data class PullRequest(
    val additions: Int,
    @SerializedName("author_association")
    val authorAssociation: String,
    @SerializedName("changed_files")
    val changedFiles: Int,
    val comments: Int,
    @SerializedName("comments_url")
    val commentsUrl: String,
    val commits: Int,
    @SerializedName("commits_url")
    val commitsUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    val deletions: Int,
    @SerializedName("diff_url")
    val diffUrl: String,
    val draft: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    @SerializedName("issue_url")
    val issueUrl: String,
    val locked: Boolean,
    @SerializedName("maintainer_can_modify")
    val maintainerCanModify: Boolean,
    @SerializedName("mergeable_state")
    val mergeableState: String,
    val merged: Boolean,
    @SerializedName("node_id")
    val nodeId: String,
    val number: Int,
    @SerializedName("patch_url")
    val patchUrl: String,
    @SerializedName("review_comment_url")
    val reviewCommentUrl: String,
    @SerializedName("review_comments")
    val reviewComments: Int,
    @SerializedName("review_comments_url")
    val reviewCommentsUrl: String,
    val state: String,
    @SerializedName("statuses_url")
    val statusesUrl: String,
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
)