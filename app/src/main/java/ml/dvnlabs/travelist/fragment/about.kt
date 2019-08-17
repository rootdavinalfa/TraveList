package ml.dvnlabs.travelist.fragment

import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation

import ml.dvnlabs.travelist.R

class About : Fragment(){
    val url : String = "https://cdn.dvnlabs.ml/dicoding/"
    var cover : ImageView? = null
    var prof : ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.about,container,false)
        cover = view.findViewById(R.id.about_cover)
        prof = view.findViewById(R.id.about_profile)
        fillImage()
        return view
    }
    private fun fillImage(){
        //Profile Image
        Glide.with(activity!!)
            .load(url+"davin.jpg")
            .transition(DrawableTransitionOptions().crossFade())
            .apply(bitmapTransform(CropCircleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(300,300))
            .into(prof!!)

        //Cover
        Glide.with(activity!!).applyDefaultRequestOptions(RequestOptions()
            .placeholder(R.drawable.ic_picture)
            .error(R.drawable.ic_picture))
            .load(url+"img/komyoji.jpg").transform(BlurTransformation(25,3))
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(cover!!)
    }
}