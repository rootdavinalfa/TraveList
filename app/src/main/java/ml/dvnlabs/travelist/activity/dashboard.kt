package ml.dvnlabs.travelist.activity

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.rv_travelist.*
import ml.dvnlabs.travelist.R
import ml.dvnlabs.travelist.model.TravelModelList
import ml.dvnlabs.travelist.recyclerview.ListAdapter
import kotlin.random.Random

class DashboardActivity : AppCompatActivity(){

    var imgurimg : String = "https://cdn.dvnlabs.ml/dicoding/img/"
    var adapter : ListAdapter? = null
    var listview : RecyclerView? = null
    var bottomNav : BottomNavigationView? = null
    var container : RelativeLayout? = null
    var models : ArrayList<TravelModelList>? = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        listview = findViewById(R.id.dashboard_list)
        bottomNav = findViewById(R.id.dashboard_nav)
        container = findViewById(R.id.dashboard_container)
        modelsAdd()
        adapter = ListAdapter(this,models!!,R.layout.rv_travelist)
        val linear = LinearLayoutManager(this)
        listview!!.layoutManager = linear
        listview!!.adapter = adapter
        /*val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                containerBg()
                handler.postDelayed(this,1000)
            }
        },1000)*/
    }

    override fun onResume() {
        super.onResume()
        containerBg()
    }
    private fun containerBg(){
        var rnd = Random.nextInt(0,models!!.size)
        println("INDEX: "+rnd)
        val multi = MultiTransformation<Bitmap>(RoundedCornersTransformation(5,0), BlurTransformation(25,3))
        Glide.with(this).applyDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_picture)
                .error(R.drawable.ic_picture))
            .load(models!![rnd].ImgUrl)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(
                RequestOptions.bitmapTransform(multi)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).override(300,400))
            .into(object : CustomTarget<Drawable>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    container!!.background = placeholder
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    container!!.background = resource
                }
            })
    }
    private fun modelsAdd(){
        models!!.add(
            TravelModelList(1,"Tokyo Tower","Tokyo Tower (東京タワー Tōkyō tawā, officially called 日本電波塔 Nippon denpatō \"Japan Radio Tower\") is a communications and observation tower in the Shiba-koen district of Minato, Tokyo, Japan. At 332.9 metres (1,092 ft), it is the second-tallest structure in Japan. The structure is an Eiffel Tower-inspired lattice tower that is painted white and international orange to comply with air safety regulations.\n" +
                    "\n" +
                    "Built in 1958, the tower's main sources of income are tourism and antenna leasing. Over 150 million people have visited the tower. FootTown, a four-story building directly under the tower, houses museums, restaurants and shops. Departing from there, guests can visit two observation decks. The two-story Main Deck (formerly known as the Main Observatory) is at 150 metres (490 ft), while the smaller Top Deck (formerly known as the Special Observatory) reaches a height of 249.6 metres (819 ft). The names were changed following renovation of the top deck in 2018.",imgurimg+"tokyotower.jpg","4-2-8 Shiba-koen, Minato, Tokyo 105-0011")
        )
        models!!.add(TravelModelList(2,"Bunaken","Bunaken is an island of 8 km2, part of the Bunaken National Marine Park. Bunaken is located at the northern tip of the island of Sulawesi, Indonesia. It belongs administratively to the municipality of Manado. Scuba diving attracts many visitors to the island.\n" +
                "\n" +
                "\n" +
                "Other sides of Bunaken.\n" +
                "Bunaken National Park extends over an area of 890.65 km2 of which only 3% is terrestrial, including Bunaken Island, as well as the islands of Manado Tua, Mantehage, Nain and Siladen.",imgurimg+"bunaken.jpg","North Sulawesi"))
        models!!.add(TravelModelList(3,"Shinjuku Gyoen National Garden","Shinjuku Gyo-en (新宿御苑) is a large park and garden in Shinjuku and Shibuya, Tokyo, Japan. It was originally a residence of the Naitō family in the Edo period. Afterwards, it became a garden under the management of the Imperial Household Agency of Japan. It is now a national park under the jurisdiction of the Ministry of the Environment.",imgurimg+"shinjukugyoen.jpg","Shinjuku Gyo-en,Shinjuku and Shibuya, Tokyo, Japan"))
        models!!.add(TravelModelList(4,"Mount Bromo","Mount Bromo (Indonesian: Gunung Bromo), is an active volcano and part of the Tengger massif, in East Java, Indonesia. At 2,329 meters (7,641 ft) it is not the highest peak of the massif, but is the most well known. The massif area is one of the most visited tourist attractions in East Java, Indonesia. The volcano belongs to the Bromo Tengger Semeru National Park. The name of Bromo derived from Javanese pronunciation of Brahma, the Hindu creator god." +
                "Mount Bromo sits in the middle of a plain called the \"Sea of Sand\" (Javanese: Segara Wedi or Indonesian: Lautan Pasir), a protected nature reserve since 1919. The typical way to visit Mount Bromo is from the nearby mountain village of Cemoro Lawang. From there it is possible to walk to the volcano in about 45 minutes, but it is also possible to take an organised jeep tour, which includes a stop at the viewpoint on Mount Penanjakan (2,770 m or 9,088 ft) (Indonesian: Gunung Penanjakan). The viewpoint on Mount Penanjakan can also be reached on foot in about two hours.",imgurimg+"bromo.jpg","Mount Bromo,East Java"))
        models!!.add(TravelModelList(5,"Kinkaku-ji","Kinkaku-ji (金閣寺, literally \"Temple of the Golden Pavilion\"), officially named Rokuon-ji (鹿苑寺, literally \"Deer Garden Temple\"), is a Zen Buddhist temple in Kyoto, Japan.It is one of the most popular buildings in Japan, attracting a large number of visitors annually.It is designated as a National Special Historic Site, a National Special Landscape and is one of 17 locations making up the Historic Monuments of Ancient Kyoto which are World Heritage Sites.",imgurimg+"kinkakuji.jpg","1 Kinkakuji-chō, Kita-ku, Kyōto, Kyoto Prefecture"))
        models!!.add(TravelModelList(6,"Arashiyama","Arashiyama is a district on the western outskirts of Kyoto, Japan. It also refers to the mountain across the Ōi River, which forms a backdrop to the district. Arashiyama is a nationally designated Historic Site and Place of Scenic Beauty.",imgurimg+"arashiyama.jpg","Ukyo Ward, Kyoto, 616-0007, Japan"))
        models!!.add(TravelModelList(7,"Kōmyō-ji","Buddhist temple site with 32 different buildings offering a vibrant display of leaves in autumn.",imgurimg+"komyoji.jpg","Japan, 〒617-0811 Kyoto, Nagaokakyo, Ao, Saijo, ノ内26-1"))
        models!!.add(TravelModelList(8,"Akiu Great Falls","Akiu Great Falls (秋保大滝 Akiu Ōtaki) are located in Sendai, Miyagi Prefecture, Japan. They are a nationally designated Place of Scenic Beauty",imgurimg+"akiu.jpg","\tTaihaku-ku, Sendai, Miyagi Prefecture, Japan"))
        models!!.add(TravelModelList(9,"Komodo National Park","Komodo National Park is a national park in Indonesia located within the Lesser Sunda Islands in the border region between the provinces of East Nusa Tenggara and West Nusa Tenggara. The park includes the three larger islands Komodo, Padar and Rinca, and 26 smaller ones, with a total area of 1,733 km²",imgurimg+"komodo.jpg","East Nusa Tenggara"))
        models!!.add(TravelModelList(10,"Itsukushima","Itsukushima, also known as Miyajima, is a small island in Hiroshima Bay, western Japan. It is known for its forests and ancient temples. Just offshore, the giant, orange Great Torii Gate is partially submerged at high tide. It marks the entrance to the Itsukushima Shrine, which was first built in the 12th century. Nearby, the Museum of History and Folklore has cultural artifacts in a 19th-century merchant's home.",imgurimg+"itsukushima.jpg","Island in Hatsukaichi, Japan"))
    }
}