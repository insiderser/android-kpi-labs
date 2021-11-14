package com.insiderser.kpi.android.lab2

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Returns a property delegate that automatically sets backing property to `null`
 * after this [Fragment]'s view gets destroyed.
 */
fun <T : Any> Fragment.viewLifecycleScoped(): ReadWriteProperty<Any, T> = ViewLifecycleScopedProperty(this)

private class ViewLifecycleScopedProperty<T : Any>(owner: Fragment) : ReadWriteProperty<Any, T>, DefaultLifecycleObserver {

    private var value: T? = null

    private val handler = Handler(Looper.getMainLooper())

    init {
        owner.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
            viewLifecycleOwner?.lifecycle?.addObserver(this)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        handler.post { value = null } // Posting because this is called before onDestroyView
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        value ?: error("${property.name} property isn't initialized. Probably called before onCreateView or after onDestroyView")

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }
}
