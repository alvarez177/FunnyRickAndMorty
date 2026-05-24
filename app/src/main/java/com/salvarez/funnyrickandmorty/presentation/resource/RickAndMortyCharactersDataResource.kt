package com.salvarez.funnyrickandmorty.presentation.resource

import android.content.Context
import com.salvarez.funnyrickandmorty.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RickAndMortyCharactersDataResource @Inject constructor(
    @ApplicationContext
    private val context: Context
) : RickAndMortyCharactersResource{
    override fun getNoInternetErrorTitle(): String {
        return context.getString(R.string.no_internet_error_title)
    }

    override fun getServerErrorTitle(): String {
        return context.getString(R.string.server_error_title)
    }

    override fun getNoDataToShowErrorTitle(): String {
        return context.getString(R.string.no_data_to_show_error_title)
    }

    override fun getUnknownErrorTitle(): String {
        return context.getString(R.string.unknown_error_title)
    }

    override fun getTryAgainErrorSubtitle(): String {
        return context.getString(R.string.try_again_later_error_subtitle)
    }

    override fun getContactToSupportErrorSubtitle(): String {
        return context.getString(R.string.contact_to_support_error_subtitle)
    }

    override fun getRetryButtonText(): String {
        return context.getString(R.string.retry_button_text)
    }

    override fun getExitAppButtonText(): String {
        return context.getString(R.string.out_button_text)
    }
}