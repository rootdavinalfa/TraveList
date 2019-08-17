package ml.dvnlabs.travelist.recyclerview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import ml.dvnlabs.travelist.R
import ml.dvnlabs.travelist.activity.Details
import ml.dvnlabs.travelist.model.TravelModelList

class ListHolder(context: Context?,view:View) : RecyclerView.ViewHolder(view) ,View.OnClickListener{

    private var desc : TextView? = view.findViewById(R.id.rv_desc)
    private var loc : TextView?= view.findViewById(R.id.rv_loc)
    private var point:TextView? = view.findViewById(R.id.rv_pointinterest)
    private var container: CardView = view.findViewById(R.id.rv_container)
    private var img: ImageView = view.findViewById(R.id.rv_img)

    private var models: TravelModelList? = null
    private var context: Context = context!!

    init {
        itemView.setOnClickListener(this)
    }
    //var view: View = view
    fun bindList(model : TravelModelList){
        models = model
        desc!!.text = models!!.description
        loc!!.text = models!!.Loc
        point!!.text = models!!.title
        val multi = MultiTransformation<Bitmap>(RoundedCornersTransformation(5,0),BlurTransformation(35))
        //For IMG cover
        Glide.with(context).applyDefaultRequestOptions(RequestOptions()
            .placeholder(R.drawable.ic_picture)
            .error(R.drawable.ic_picture))
            .load(models!!.ImgUrl)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(bitmapTransform(RoundedCornersTransformation(15,0))
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(300,400))
            .into(img)

        //For BG
        Glide.with(context).applyDefaultRequestOptions(RequestOptions()
            .placeholder(R.drawable.ic_picture)
            .error(R.drawable.ic_picture))
            .load(models!!.ImgUrl)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(bitmapTransform(multi)
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(300,400))
            .into(object : CustomTarget<Drawable>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    container.background = placeholder
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    container.background = resource
                }
            })

    }

    override fun onClick(v: View?) {
        if (models != null){
            val intent = Intent(context,Details::class.java)
            intent.putExtra("title",models!!.title)
            intent.putExtra("description",models!!.description)
            intent.putExtra("imgurl",models!!.ImgUrl)
            intent.putExtra("location",models!!.Loc)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}