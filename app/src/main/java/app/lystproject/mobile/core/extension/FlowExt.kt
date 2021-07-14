package app.lystproject.mobile.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

val Fragment.viewScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope
