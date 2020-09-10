package co.id.aminfaruq.core.ui.base

import co.id.aminfaruq.core.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadmoreItemView : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int = R.layout.load_more_progress
}