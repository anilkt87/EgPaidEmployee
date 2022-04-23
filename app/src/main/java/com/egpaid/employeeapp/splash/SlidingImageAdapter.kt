package com.egpaid.employeeapp.splash

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.widget.ImageView
import android.widget.TextView
import com.egpaid.employeeapp.R


class SlidingImageAdapter(private val context: Context, private val welcomedata: ArrayList<WelcomeModel>) :
    PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var imageLayout: View

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        imageLayout = layoutInflater.inflate(R.layout.the_items_layout, container, false)
//        val featuredImage: ImageView = imageLayout.findViewById(R.id.my_featured_image)
//        val featuredText : TextView =   imageLayout.findViewById(R.id.my_festured_desc)
//        val welcomeModel : WelcomeModel = welcomedata[position]
//        featuredImage.setImageResource(welcomeModel.screenimage!!)
//        featuredText.text = welcomeModel.desc
//        container.addView(imageLayout, 0)
        return imageLayout
    }

    override fun getCount(): Int {
        return welcomedata.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}