package ml.dvnlabs.travelist.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import ml.dvnlabs.travelist.R
import ml.dvnlabs.travelist.model.TravelModelList

class Details : AppCompatActivity(){
    var container : LinearLayout? = null
    var detailsContainer : LinearLayout? = null
    var coverSmall : ImageView? = null
    var coverFull : ImageView? = null
    var titleText : TextView? = null
    var descText : TextView? = null
    var locaText : TextView? = null
    var toMap : CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        container = findViewById(R.id.container)
        detailsContainer = findViewById(R.id.details_container)
        coverFull = findViewById(R.id.details_cover_full)
        coverSmall = findViewById(R.id.details_cover_small)
        titleText = findViewById(R.id.details_title)
        descText = findViewById(R.id.details_description)
        locaText = findViewById(R.id.details_location)
        toMap = findViewById(R.id.details_location_tomap)
        if (intent != null){
            val bundle : Bundle = intent.extras!!
            val title = bundle.getString("title")
            val description = bundle.getString("description")
            val imgurl = bundle.getString("imgurl")
            val localt = bundle.getString("location")
            titleText!!.text = title
            descText!!.text = description
            locaText!!.text = localt

            //BG
            Glide.with(this).applyDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_picture)
                    .error(R.drawable.ic_picture))
                .load(imgurl).transform(BlurTransformation(35))
                .thumbnail(0.5f)
                .transition(DrawableTransitionOptions().crossFade())
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(object : CustomTarget<Drawable>(){
                    override fun onLoadCleared(placeholder: Drawable?) {
                        container!!.background = placeholder
                    }

                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        container!!.background = resource
                    }
                })

            //Smallcover
            Glide.with(this).applyDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_picture)
                .error(R.drawable.ic_picture))
                .load(imgurl)
                .transition(DrawableTransitionOptions().crossFade())
                .apply(
                    RequestOptions.bitmapTransform(RoundedCornersTransformation(15, 0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL).override(300,400))
                .into(coverSmall!!)

            //Fullcover
            Glide.with(this).applyDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_picture)
                .error(R.drawable.ic_picture))
                .load(imgurl)
                .transition(DrawableTransitionOptions().crossFade())
                .apply(RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(coverFull!!)

            toMap!!.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    val gmmIntentUri = Uri.parse("geo:0,0?q="+localt)
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    if (mapIntent.resolveActivity(packageManager) != null) {
                        startActivity(mapIntent)
                    }
                }
            })

        }
    }

}