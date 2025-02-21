package p.ji.kr.practice.screen

import androidx.annotation.StringRes
import p.ji.kr.practice.R

sealed class AppScreen(
    val route: String,
    @StringRes val resourceId: Int
) {
    data object Home: AppScreen("home", R.string.home)
    data object Favorite: AppScreen("favorite", R.string.favorite)
    data object MyPage: AppScreen("mypage", R.string.my_page)
}