package app.lystproject.mobile.core.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

fun Fragment.onBackPress(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        onBackPressed = onBackPressed
    )
}

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

inline var View.show: Boolean
    get() = visibility == View.VISIBLE
    set(shouldShow) {
        visibility = if (shouldShow) View.VISIBLE else View.INVISIBLE
    }

inline val EditText.lazyText: () -> String
    get() = { this.text.trim().toString() }
