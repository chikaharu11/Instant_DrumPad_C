package jp.chikaharu11.instant_drumpad_drums

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.*
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.arthenica.mobileffmpeg.FFmpeg
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), CustomAdapterListener {

    private lateinit var adViewContainer: FrameLayout
    private lateinit var admobmAdView: AdView

    private lateinit var mediaProjectionManager: MediaProjectionManager

    private val handler = Handler()
    private var audioName = ""
    var start = 0
    var stop = 0

    companion object {
        private const val READ_REQUEST_CODE2: Int = 43
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 41
        private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 42
        private const val MEDIA_PROJECTION_REQUEST_CODE = 13
    }

    fun selectAudio() {
        val uri = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAndroid%2Fdata%2Fjp.chikaharu11.instant_drumpad_drums%2Ffiles%2FMusic")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
            type = "audio/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE2)
    }

    fun selectEX() {
        if (!isReadExternalStoragePermissionGranted()) {
            requestReadExternalStoragePermission()
        } else {
            tSoundList.clear()
            val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                tSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()
        }
    }

    private lateinit var soundPool: SoundPool

    private lateinit var mp: MediaPlayer

    private lateinit var lmp: LoopMediaPlayer

    private lateinit var aCustomAdapter: CustomAdapter
    private lateinit var bCustomAdapter: CustomAdapter
    private lateinit var cCustomAdapter: CustomAdapter
    private lateinit var dCustomAdapter: CustomAdapter
    private lateinit var eCustomAdapter: CustomAdapter
    private lateinit var fCustomAdapter: CustomAdapter
    private lateinit var gCustomAdapter: CustomAdapter
    private lateinit var hCustomAdapter: CustomAdapter
    private lateinit var iCustomAdapter: CustomAdapter
    private lateinit var jCustomAdapter: CustomAdapter
    private lateinit var kCustomAdapter: CustomAdapter
    private lateinit var lCustomAdapter: CustomAdapter
    private lateinit var mCustomAdapter: CustomAdapter
    private lateinit var aaCustomAdapter: CustomAdapter
    private lateinit var abCustomAdapter: CustomAdapter
    private lateinit var acCustomAdapter: CustomAdapter

    private lateinit var nCustomAdapter: CustomAdapter
    private lateinit var oCustomAdapter: CustomAdapter
    private lateinit var pCustomAdapter: CustomAdapter
    private lateinit var qCustomAdapter: CustomAdapter
    private lateinit var rCustomAdapter: CustomAdapter
    private lateinit var baCustomAdapter: CustomAdapter
    private lateinit var bbCustomAdapter: CustomAdapter

    private lateinit var sCustomAdapter: CustomAdapter
    private lateinit var tCustomAdapter: CustomAdapter

    private lateinit var aSoundList: MutableList<SoundList>
    private lateinit var bSoundList: MutableList<SoundList>
    private lateinit var cSoundList: MutableList<SoundList>
    private lateinit var dSoundList: MutableList<SoundList>
    private lateinit var eSoundList: MutableList<SoundList>
    private lateinit var fSoundList: MutableList<SoundList>
    private lateinit var gSoundList: MutableList<SoundList>
    private lateinit var hSoundList: MutableList<SoundList>
    private lateinit var iSoundList: MutableList<SoundList>
    private lateinit var jSoundList: MutableList<SoundList>
    private lateinit var kSoundList: MutableList<SoundList>
    private lateinit var lSoundList: MutableList<SoundList>
    private lateinit var mSoundList: MutableList<SoundList>
    private lateinit var aaSoundList: MutableList<SoundList>
    private lateinit var abSoundList: MutableList<SoundList>
    private lateinit var acSoundList: MutableList<SoundList>

    private lateinit var nSoundList: MutableList<SoundList>
    private lateinit var oSoundList: MutableList<SoundList>
    private lateinit var pSoundList: MutableList<SoundList>
    private lateinit var qSoundList: MutableList<SoundList>
    private lateinit var rSoundList: MutableList<SoundList>
    private lateinit var baSoundList: MutableList<SoundList>
    private lateinit var bbSoundList: MutableList<SoundList>

    private lateinit var sSoundList: MutableList<SoundList>
    private lateinit var tSoundList: MutableList<SoundList>

    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound5 = 0
    private var sound6 = 0
    private var sound7 = 0
    private var sound8 = 0
    private var sound9 = 0
    private var sound10 = 0
    private var sound11 = 0
    private var sound12 = 0
    private var sound13 = 0
    private var sound14 = 0
    private var sound15 = 0
    private var sound16 = 0


    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdMob()
        loadAdMob()

        textView.text = "cycdh_trasha_07"
        textView2.text = "cycdh_multicrashlo_01"
        textView3.text = "cycdh_crash_01"
        textView4.text = "khats_clsd_02"
        textView5.text = "cycdh_ludflama_04"
        textView6.text = "cycdh_sab_hfhat_02"
        textView7.text = "pearlpiccolo_side_snron_01"
        textView8.text = "cycdh_ludrimc_03"
        textView9.text = "cycdh_ludflamb_02"
        textView10.text = "cycdh_ludrima_01"
        textView11.text = "cycdh_acoukick_12"
        textView12.text = "acoustic_snare_03"
        textView13.text = "cycdh_loosekick_04"
        textView14.text = "cycdh_keskick_08"
        textView15.text = "cycdh_loosekick_07"


        aSoundList = arrayListOf(
            SoundList("cycdh_crash_01.ogg"),
            SoundList("cycdh_crash_02.ogg"),
            SoundList("cycdh_crash_03.ogg"),
            SoundList("cycdh_multicrash_01.ogg"),
            SoundList("cycdh_multicrash_02.ogg"),
            SoundList("cycdh_multicrash_03.ogg"),
            SoundList("cycdh_multicrashhi_01.ogg"),
            SoundList("cycdh_multicrashhi_02.ogg"),
            SoundList("cycdh_multicrashhi_03.ogg"),
            SoundList("cycdh_multicrashlo_01.ogg"),
            SoundList("cycdh_multicrashlo_02.ogg"),
            SoundList("cycdh_multicrashlo_03.ogg")
                )

        bSoundList = arrayListOf(
            SoundList("cycdh_trasha_01.ogg"),
            SoundList("cycdh_trasha_02.ogg"),
            SoundList("cycdh_trasha_03.ogg"),
            SoundList("cycdh_trasha_04.ogg"),
            SoundList("cycdh_trasha_05.ogg"),
            SoundList("cycdh_trasha_06.ogg"),
            SoundList("cycdh_trasha_07.ogg"),
            SoundList("cycdh_trasha_08.ogg"),
            SoundList("cycdh_trasha_09.ogg"),
            SoundList("cycdh_trashb_01.ogg"),
            SoundList("cycdh_trashb_02.ogg"),
            SoundList("cycdh_trashb_03.ogg"),
            SoundList("cycdh_trashc_01.ogg"),
            SoundList("cycdh_trashc_02.ogg"),
            SoundList("cycdh_trashc_03.ogg"),
            SoundList("cycdh_trashc_04.ogg"),
            SoundList("cycdh_trashd_01.ogg"),
            SoundList("cycdh_trashd_02.ogg"),
            SoundList("cycdh_trashe_01.ogg"),
            SoundList("cycdh_trashe_02.ogg"),
            SoundList("cycdh_trashe_03.ogg"),
            SoundList("cycdh_trashe_04.ogg"),
            SoundList("cycdh_trashe_05.ogg"),
            SoundList("cycdh_trashf_01.ogg"),
            SoundList("cycdh_trashf_02.ogg"),
            SoundList("cycdh_trashf_03.ogg"),
            SoundList("cycdh_trashf_04.ogg")
        )
        cSoundList = arrayListOf(
            SoundList("acoustic_hat_01.ogg"),
            SoundList("acoustic_hat_02.ogg"),
            SoundList("acoustic_hat_03.ogg"),
            SoundList("acoustic_hat_04.ogg"),
            SoundList("acoustic_hat_05.ogg"),
            SoundList("acoustic_hat_06.ogg")
            )
        dSoundList = arrayListOf(
            SoundList("cycdh_sab_clhat_01.ogg"),
            SoundList("cycdh_sab_clhat_02.ogg"),
            SoundList("cycdh_sab_clhat_03.ogg"),
            SoundList("cycdh_sab_clhat_04.ogg"),
            SoundList("cycdh_sab_clhat_05.ogg"),
            SoundList("cycdh_sab_clhat_06.ogg"),
            SoundList("cycdh_sab_clhat_07.ogg"),
            SoundList("cycdh_sab_clhat_08.ogg"),
            SoundList("cycdh_sab_clhat_09.ogg"),
            SoundList("cycdh_sab_clhat_10.ogg"),
            SoundList("cycdh_sab_clhat_11.ogg"),
            SoundList("cycdh_sab_clhat_12.ogg"),
            SoundList("cycdh_sab_clhat_13.ogg"),
            SoundList("cycdh_sab_clhat_14.ogg"),
            SoundList("cycdh_sab_hfhat_01.ogg"),
            SoundList("cycdh_sab_hfhat_02.ogg"),
            SoundList("cycdh_sab_hfhat_03.ogg"),
            SoundList("cycdh_sab_hfhat_04.ogg"),
            SoundList("cycdh_sab_ophat_01.ogg"),
            SoundList("cycdh_sab_ophat_02.ogg"),
            SoundList("cycdh_sab_ophat_03.ogg"),
            SoundList("cycdh_sab_ophat_04.ogg"),
            SoundList("cycdh_sab_ophat_05.ogg"),
            SoundList("cycdh_sab_ophat_06.ogg"),
            SoundList("cycdh_sab_ophat_07.ogg"),
            SoundList("cycdh_sab_ophat_08.ogg"),
            SoundList("cycdh_sab_ophat_09.ogg"),
            SoundList("cycdh_sab_pdhat_01.ogg"),
            SoundList("cycdh_sab_pdhat_02.ogg")
        )
        eSoundList = arrayListOf(
            SoundList("khats_clsd_01.ogg"),
            SoundList("khats_clsd_02.ogg"),
            SoundList("khats_clsd_03.ogg"),
            SoundList("khats_clsd_04.ogg"),
            SoundList("khats_clsd_05.ogg"),
            SoundList("khats_clsd_06.ogg"),
            SoundList("khats_clsd_07.ogg"),
            SoundList("khats_clsd_08.ogg"),
            SoundList("khats_clsd_09.ogg"),
            SoundList("khats_clsd_10.ogg"),
            SoundList("khats_clsd_11.ogg"),
            SoundList("khats_hlfop_01.ogg"),
            SoundList("khats_hlfop_02.ogg"),
            SoundList("khats_hlfop_03.ogg"),
            SoundList("khats_open_01.ogg"),
            SoundList("khats_open_02.ogg"),
            SoundList("khats_open_03.ogg"),
            SoundList("khats_open_04.ogg"),
            SoundList("khats_open_05.ogg"),
            SoundList("khats_open_06.ogg"),
            SoundList("khats_open_07.ogg"),
            SoundList("khats_open_08.ogg"),
            SoundList("khats_open_09.ogg"),
            SoundList("khats_pdl_01.ogg"),
            SoundList("khats_pdl_02.ogg"),
            SoundList("khats_pdl_03.ogg"),
            SoundList("khats_pdl_04.ogg")
        )
        fSoundList = arrayListOf(
            SoundList("cycdh_acoukick_01.ogg"),
            SoundList("cycdh_acoukick_02.ogg"),
            SoundList("cycdh_acoukick_03.ogg"),
            SoundList("cycdh_acoukick_04.ogg"),
            SoundList("cycdh_acoukick_05.ogg"),
            SoundList("cycdh_acoukick_06.ogg"),
            SoundList("cycdh_acoukick_07.ogg"),
            SoundList("cycdh_acoukick_08.ogg"),
            SoundList("cycdh_acoukick_09.ogg"),
            SoundList("cycdh_acoukick_10.ogg"),
            SoundList("cycdh_acoukick_11.ogg"),
            SoundList("cycdh_acoukick_12.ogg"),
            SoundList("cycdh_acoukick_13.ogg"),
            SoundList("cycdh_acoukick_14.ogg"),
            SoundList("cycdh_acoukick_15.ogg"),
            SoundList("cycdh_acoukick_16.ogg"),
            SoundList("cycdh_acoukick_17.ogg"),
            SoundList("cycdh_acoukick_18.ogg"),
            SoundList("cycdh_acoukick_19.ogg"),
            SoundList("cycdh_acoukick_20.ogg")
        )
        gSoundList = arrayListOf(
            SoundList("cycdh_keskick_01.ogg"),
            SoundList("cycdh_keskick_02.ogg"),
            SoundList("cycdh_keskick_03.ogg"),
            SoundList("cycdh_keskick_04.ogg"),
            SoundList("cycdh_keskick_05.ogg"),
            SoundList("cycdh_keskick_06.ogg"),
            SoundList("cycdh_keskick_07.ogg"),
            SoundList("cycdh_keskick_08.ogg")
        )
        hSoundList = arrayListOf(
            SoundList("cycdh_loosekick_01.ogg"),
            SoundList("cycdh_loosekick_02.ogg"),
            SoundList("cycdh_loosekick_03.ogg"),
            SoundList("cycdh_loosekick_04.ogg"),
            SoundList("cycdh_loosekick_05.ogg"),
            SoundList("cycdh_loosekick_06.ogg"),
            SoundList("cycdh_loosekick_07.ogg"),
            SoundList("cycdh_loosekick_08.ogg")
        )
        iSoundList = arrayListOf(
            SoundList("acoustic_snare_01.ogg"),
            SoundList("acoustic_snare_02.ogg"),
            SoundList("acoustic_snare_03.ogg"),
            SoundList("acoustic_snare_04.ogg")
        )
        jSoundList = arrayListOf(
            SoundList("cycdh_ludflama_01.ogg"),
            SoundList("cycdh_ludflama_02.ogg"),
            SoundList("cycdh_ludflama_03.ogg"),
            SoundList("cycdh_ludflama_04.ogg"),
            SoundList("cycdh_ludflama_05.ogg"),
            SoundList("cycdh_ludrima_01.ogg"),
            SoundList("cycdh_ludrima_02.ogg"),
            SoundList("cycdh_ludrima_03.ogg"),
            SoundList("cycdh_ludrima_04.ogg"),
            SoundList("cycdh_ludrima_05.ogg"),
            SoundList("cycdh_ludrima_06.ogg"),
            SoundList("cycdh_ludrima_07.ogg"),
            SoundList("cycdh_ludsdsta_01.ogg"),
            SoundList("cycdh_ludsdsta_02.ogg"),
            SoundList("cycdh_ludsdsta_03.ogg"),
            SoundList("cycdh_ludsdsta_04.ogg"),
            SoundList("cycdh_ludsdsta_05.ogg"),
            SoundList("cycdh_ludsdsta_06.ogg"),
            SoundList("cycdh_ludsdsta_07.ogg"),
            SoundList("cycdh_ludsnra_01.ogg"),
            SoundList("cycdh_ludsnra_02.ogg"),
            SoundList("cycdh_ludsnra_03.ogg"),
            SoundList("cycdh_ludsnra_04.ogg"),
            SoundList("cycdh_ludsnra_05.ogg"),
            SoundList("cycdh_ludsnroffa_01.ogg"),
            SoundList("cycdh_ludsnroffa_02.ogg"),
            SoundList("cycdh_ludsnroffa_03.ogg"),
            SoundList("cycdh_ludsnroffa_04.ogg"),
            SoundList("cycdh_ludsnroffa_05.ogg"),
            SoundList("cycdh_ludsnroffa_06.ogg"),
            SoundList("cycdh_ludsnroffa_07.ogg"),
            SoundList("cycdh_ludsnroffa_08.ogg")
        )
        kSoundList = arrayListOf(
            SoundList("cycdh_ludflamb_01.ogg"),
            SoundList("cycdh_ludflamb_02.ogg"),
            SoundList("cycdh_ludflamb_03.ogg"),
            SoundList("cycdh_ludflamb_04.ogg"),
            SoundList("cycdh_ludflamb_05.ogg"),
            SoundList("cycdh_ludrimb_01.ogg"),
            SoundList("cycdh_ludrimb_02.ogg"),
            SoundList("cycdh_ludrimb_03.ogg"),
            SoundList("cycdh_ludrimb_04.ogg"),
            SoundList("cycdh_ludrimb_05.ogg"),
            SoundList("cycdh_ludrimb_06.ogg"),
            SoundList("cycdh_ludrimb_07.ogg"),
            SoundList("cycdh_ludsdstb_01.ogg"),
            SoundList("cycdh_ludsdstb_02.ogg"),
            SoundList("cycdh_ludsdstb_03.ogg"),
            SoundList("cycdh_ludsdstb_04.ogg"),
            SoundList("cycdh_ludsdstb_05.ogg"),
            SoundList("cycdh_ludsdstb_06.ogg"),
            SoundList("cycdh_ludsdstb_07.ogg"),
            SoundList("cycdh_ludsnrb_01.ogg"),
            SoundList("cycdh_ludsnrb_02.ogg"),
            SoundList("cycdh_ludsnrb_03.ogg"),
            SoundList("cycdh_ludsnrb_04.ogg"),
            SoundList("cycdh_ludsnrb_05.ogg"),
            SoundList("cycdh_ludsnroffb_01.ogg"),
            SoundList("cycdh_ludsnroffb_02.ogg"),
            SoundList("cycdh_ludsnroffb_03.ogg"),
            SoundList("cycdh_ludsnroffb_04.ogg"),
            SoundList("cycdh_ludsnroffb_05.ogg"),
            SoundList("cycdh_ludsnroffb_06.ogg"),
            SoundList("cycdh_ludsnroffb_07.ogg"),
            SoundList("cycdh_ludsnroffb_08.ogg")
        )
        lSoundList = arrayListOf(
            SoundList("cycdh_ludflamc_01.ogg"),
            SoundList("cycdh_ludflamc_02.ogg"),
            SoundList("cycdh_ludflamc_03.ogg"),
            SoundList("cycdh_ludflamc_04.ogg"),
            SoundList("cycdh_ludflamc_05.ogg"),
            SoundList("cycdh_ludrimc_01.ogg"),
            SoundList("cycdh_ludrimc_02.ogg"),
            SoundList("cycdh_ludrimc_03.ogg"),
            SoundList("cycdh_ludrimc_04.ogg"),
            SoundList("cycdh_ludrimc_05.ogg"),
            SoundList("cycdh_ludrimc_06.ogg"),
            SoundList("cycdh_ludrimc_07.ogg"),
            SoundList("cycdh_ludsdstc_01.ogg"),
            SoundList("cycdh_ludsdstc_02.ogg"),
            SoundList("cycdh_ludsdstc_03.ogg"),
            SoundList("cycdh_ludsdstc_04.ogg"),
            SoundList("cycdh_ludsdstc_05.ogg"),
            SoundList("cycdh_ludsdstc_06.ogg"),
            SoundList("cycdh_ludsdstc_07.ogg"),
            SoundList("cycdh_ludsnrc_01.ogg"),
            SoundList("cycdh_ludsnrc_02.ogg"),
            SoundList("cycdh_ludsnrc_03.ogg"),
            SoundList("cycdh_ludsnrc_04.ogg"),
            SoundList("cycdh_ludsnrc_05.ogg"),
            SoundList("cycdh_ludsnroffc_01.ogg"),
            SoundList("cycdh_ludsnroffc_02.ogg"),
            SoundList("cycdh_ludsnroffc_03.ogg"),
            SoundList("cycdh_ludsnroffc_04.ogg"),
            SoundList("cycdh_ludsnroffc_05.ogg"),
            SoundList("cycdh_ludsnroffc_06.ogg"),
            SoundList("cycdh_ludsnroffc_07.ogg"),
            SoundList("cycdh_ludsnroffc_08.ogg")
        )
        mSoundList = arrayListOf(
            SoundList("pearlpiccolo_side_snroff_01.ogg"),
            SoundList("pearlpiccolo_side_snroff_02.ogg"),
            SoundList("pearlpiccolo_side_snroff_03.ogg"),
            SoundList("pearlpiccolo_side_snroff_04.ogg"),
            SoundList("pearlpiccolo_side_snroff_05.ogg"),
            SoundList("pearlpiccolo_side_snron_01.ogg"),
            SoundList("pearlpiccolo_side_snron_02.ogg"),
            SoundList("pearlpiccolo_side_snron_03.ogg"),
            SoundList("pearlpiccolo_side_snron_04.ogg"),
            SoundList("pearlpiccolo_side_snron_05.ogg"),
            SoundList("pearlpiccolo_side_snron_06.ogg"),
            SoundList("pearlpiccolo_side_snron_07.ogg"),
            SoundList("pearlpiccolo_side_snron_08.ogg"),
            SoundList("pearlpiccolo_side_snron_09.ogg")
            )
        aaSoundList = arrayListOf(
            SoundList("cycdh_sonflam_01.ogg"),
            SoundList("cycdh_sonflam_02.ogg"),
            SoundList("cycdh_sonflam_03.ogg"),
            SoundList("cycdh_sonrim_01.ogg"),
            SoundList("cycdh_sonrim_02.ogg"),
            SoundList("cycdh_sonrim_03.ogg"),
            SoundList("cycdh_sonrim_04.ogg"),
            SoundList("cycdh_sonrim_05.ogg"),
            SoundList("cycdh_sonrim_06.ogg"),
            SoundList("cycdh_sonrim_07.ogg"),
            SoundList("cycdh_sonrim_08.ogg"),
            SoundList("cycdh_sonrim_09.ogg"),
            SoundList("cycdh_sonrim_10.ogg"),
            SoundList("cycdh_sonsdst_01.ogg"),
            SoundList("cycdh_sonsdst_02.ogg"),
            SoundList("cycdh_sonsdst_03.ogg"),
            SoundList("cycdh_sonsdst_04.ogg"),
            SoundList("cycdh_sonsdst_05.ogg"),
            SoundList("cycdh_sonsdst_06.ogg"),
            SoundList("cycdh_sonsdst_07.ogg"),
            SoundList("cycdh_sonsnr_01.ogg"),
            SoundList("cycdh_sonsnr_02.ogg"),
            SoundList("cycdh_sonsnr_03.ogg"),
            SoundList("cycdh_sonsnr_04.ogg"),
            SoundList("cycdh_sonsnr_05.ogg"),
            SoundList("cycdh_sonsnr_06.ogg"),
            SoundList("cycdh_sonsnroff_01.ogg"),
            SoundList("cycdh_sonsnroff_02.ogg"),
            SoundList("cycdh_sonsnroff_03.ogg"),
            SoundList("cycdh_sonsnroff_04.ogg"),
            SoundList("cycdh_sonsnroff_05.ogg"),
            SoundList("cycdh_sonsnroff_06.ogg"),
            SoundList("cycdh_sonupsd_01.ogg"),
            SoundList("cycdh_sonupsd_02.ogg"),
            SoundList("cycdh_sonupsd_03.ogg"),
            SoundList("cycdh_sonupsd_04.ogg"),
            SoundList("cycdh_sonupsd_05.ogg"),
            SoundList("cycdh_sonupsd_06.ogg"),
            SoundList("cycdh_sonupsd_07.ogg"),
            SoundList("cycdh_sonupsd_08.ogg")
        )
        abSoundList = arrayListOf(
            SoundList("cycdh_tamflam_01.ogg"),
            SoundList("cycdh_tamflam_02.ogg"),
            SoundList("cycdh_tamflam_03.ogg"),
            SoundList("cycdh_tamrim_01.ogg"),
            SoundList("cycdh_tamrim_02.ogg"),
            SoundList("cycdh_tamrim_03.ogg"),
            SoundList("cycdh_tamrim_04.ogg"),
            SoundList("cycdh_tamrim_05.ogg"),
            SoundList("cycdh_tamrim_06.ogg"),
            SoundList("cycdh_tamrim_07.ogg"),
            SoundList("cycdh_tamrim_08.ogg"),
            SoundList("cycdh_tamrimtgt_01.ogg"),
            SoundList("cycdh_tamrimtgt_02.ogg"),
            SoundList("cycdh_tamrimtgt_03.ogg"),
            SoundList("cycdh_tamrimtgt_04.ogg"),
            SoundList("cycdh_tamrimtgt_05.ogg"),
            SoundList("cycdh_tamsdst_01.ogg"),
            SoundList("cycdh_tamsdst_02.ogg"),
            SoundList("cycdh_tamsdst_03.ogg"),
            SoundList("cycdh_tamsdst_04.ogg"),
            SoundList("cycdh_tamsdst_05.ogg"),
            SoundList("cycdh_tamsdst_06.ogg"),
            SoundList("cycdh_tamsdst_07.ogg"),
            SoundList("cycdh_tamsdst_08.ogg"),
            SoundList("cycdh_tamsnr_01.ogg"),
            SoundList("cycdh_tamsnr_02.ogg"),
            SoundList("cycdh_tamsnr_03.ogg"),
            SoundList("cycdh_tamsnr_04.ogg"),
            SoundList("cycdh_tamsnr_05.ogg"),
            SoundList("cycdh_tamsnr_06.ogg"),
            SoundList("cycdh_tamsnr_07.ogg"),
            SoundList("cycdh_tamsnr_08.ogg"),
            SoundList("cycdh_tamsnred_01.ogg"),
            SoundList("cycdh_tamsnred_02.ogg"),
            SoundList("cycdh_tamsnred_03.ogg"),
            SoundList("cycdh_tamsnred_04.ogg"),
            SoundList("cycdh_tamsnred_05.ogg"),
            SoundList("cycdh_tamsnred_06.ogg"),
            SoundList("cycdh_tamsnred_07.ogg"),
            SoundList("cycdh_tamsnred_08.ogg"),
            SoundList("cycdh_tamsnrtgt_01.ogg"),
            SoundList("cycdh_tamsnrtgt_02.ogg"),
            SoundList("cycdh_tamsnrtgt_03.ogg"),
            SoundList("cycdh_tamsnrtgt_04.ogg"),
            SoundList("cycdh_tamsnrtgt_05.ogg"),
            SoundList("cycdh_tamsnrtgt_06.ogg"),
            SoundList("cycdh_tamsnrtgt_07.ogg"),
            SoundList("cycdh_tamupsd_01.ogg"),
            SoundList("cycdh_tamupsd_02.ogg"),
            SoundList("cycdh_tamupsd_03.ogg"),
            SoundList("cycdh_tamupsd_04.ogg"),
            SoundList("cycdh_tamupsd_05.ogg"),
            SoundList("cycdh_tamupsd_06.ogg"),
            SoundList("cycdh_tamupsd_07.ogg"),
            SoundList("cycdh_tamupsd_08.ogg"),
            SoundList("cycdh_tamupsd_09.ogg")
        )
        acSoundList = arrayListOf(
            SoundList("cycdh_piccoloa_01.ogg"),
            SoundList("cycdh_piccoloa_02.ogg"),
            SoundList("cycdh_piccoloa_03.ogg"),
            SoundList("cycdh_piccoloa_04.ogg"),
            SoundList("cycdh_piccolob_01.ogg"),
            SoundList("cycdh_piccolob_02.ogg"),
            SoundList("cycdh_piccolob_03.ogg"),
            SoundList("cycdh_piccolob_04.ogg"),
            SoundList("cycdh_piccoloc_01.ogg"),
            SoundList("cycdh_piccoloc_02.ogg"),
            SoundList("cycdh_piccoloc_03.ogg"),
            SoundList("cycdh_piccoloc_04.ogg"),
            SoundList("cycdh_piccolod_01.ogg"),
            SoundList("cycdh_piccolod_02.ogg"),
            SoundList("cycdh_piccolod_03.ogg"),
            SoundList("cycdh_piccolod_04.ogg"),
            SoundList("cycdh_piccoloe_01.ogg"),
            SoundList("cycdh_piccoloe_02.ogg"),
            SoundList("cycdh_piccoloe_03.ogg"),
            SoundList("cycdh_piccoloe_04.ogg")
        )
        nSoundList = arrayListOf(
            SoundList("bass_85_01.ogg"),
            SoundList("bass_85_02.ogg"),
            SoundList("bass_85_03.ogg"),
            SoundList("bass_90_01.ogg"),
            SoundList("bass_90_02.ogg"),
            SoundList("bass_90_03.ogg"),
            SoundList("bass_90_04.ogg"),
            SoundList("bass_90_05.ogg"),
            SoundList("bass_95_01.ogg"),
            SoundList("bass_95_02.ogg"),
            SoundList("bass_95_03.ogg"),
            SoundList("bass_100_01.ogg"),
            SoundList("bass_100_02.ogg"),
            SoundList("bass_100_03.ogg"),
            SoundList("bass_100_04.ogg"),
            SoundList("bass_100_05.ogg"),
            SoundList("bass_110_01.ogg"),
            SoundList("bass_110_02.ogg"),
            SoundList("bass_110_03.ogg"),
            SoundList("bass_110_04.ogg"),
            SoundList("bass_110_05.ogg"),
            SoundList("bass_110_06.ogg"),
            SoundList("bass_110_07.ogg"),
            SoundList("bass_110_08.ogg"),
            SoundList("bass_120_01.ogg"),
            SoundList("bass_120_02.ogg"),
            SoundList("bass_120_03.ogg"),
            SoundList("bass_120_04.ogg"),
            SoundList("bass_120_05.ogg"),
            SoundList("bass_120_06.ogg"),
            SoundList("bass_120_07.ogg"),
            SoundList("bass_120_08.ogg")
            )
        oSoundList = arrayListOf(
            SoundList("drums_85_01.ogg"),
            SoundList("drums_85_02.ogg"),
            SoundList("drums_90_01.ogg"),
            SoundList("drums_90_02.ogg"),
            SoundList("drums_90_03.ogg"),
            SoundList("drums_90_04.ogg"),
            SoundList("drums_90_05.ogg"),
            SoundList("drums_90_06.ogg"),
            SoundList("drums_90_07.ogg"),
            SoundList("drums_90_08.ogg"),
            SoundList("drums_90_09.ogg"),
            SoundList("drums_90_10.ogg"),
            SoundList("drums_90_11.ogg"),
            SoundList("drums_90_12.ogg"),
            SoundList("drums_90_13.ogg"),
            SoundList("drums_90_14.ogg"),
            SoundList("drums_90_15.ogg"),
            SoundList("drums_95_01.ogg"),
            SoundList("drums_95_02.ogg"),
            SoundList("drums_95_03.ogg"),
            SoundList("drums_95_04.ogg"),
            SoundList("drums_95_05.ogg"),
            SoundList("drums_95_06.ogg"),
            SoundList("drums_95_07.ogg"),
            SoundList("drums_95_08.ogg"),
            SoundList("drums_95_09.ogg"),
            SoundList("drums_95_10.ogg"),
            SoundList("drums_95_11.ogg"),
            SoundList("drums_95_12.ogg"),
            SoundList("drums_95_13.ogg"),
            SoundList("drums_95_14.ogg"),
            SoundList("drums_95_15.ogg"),
            SoundList("drums_95_16.ogg"),
            SoundList("drums_95_17.ogg"),
            SoundList("drums_95_18.ogg"),
            SoundList("drums_95_19.ogg"),
            SoundList("drums_100_01.ogg"),
            SoundList("drums_100_02.ogg"),
            SoundList("drums_100_03.ogg"),
            SoundList("drums_100_04.ogg"),
            SoundList("drums_100_05.ogg"),
            SoundList("drums_100_06.ogg"),
            SoundList("drums_100_07.ogg"),
            SoundList("drums_100_08.ogg"),
            SoundList("drums_110_01.ogg"),
            SoundList("drums_110_02.ogg"),
            SoundList("drums_110_03.ogg"),
            SoundList("drums_110_04.ogg"),
            SoundList("drums_110_05.ogg"),
            SoundList("drums_110_06.ogg"),
            SoundList("drums_110_07.ogg"),
            SoundList("drums_110_08.ogg"),
            SoundList("drums_110_09.ogg"),
            SoundList("drums_110_10.ogg"),
            SoundList("drums_110_11.ogg"),
            SoundList("drums_110_12.ogg"),
            SoundList("drums_110_13.ogg"),
            SoundList("drums_110_14.ogg"),
            SoundList("drums_110_15.ogg"),
            SoundList("drums_110_16.ogg"),
            SoundList("drums_110_17.ogg"),
            SoundList("drums_110_18.ogg"),
            SoundList("drums_110_19.ogg"),
            SoundList("drums_110_20.ogg"),
            SoundList("drums_110_21.ogg"),
            SoundList("drums_110_22.ogg"),
            SoundList("drums_110_23.ogg"),
            SoundList("drums_110_24.ogg"),
            SoundList("drums_110_25.ogg"),
            SoundList("drums_110_26.ogg"),
            SoundList("drums_120_01.ogg"),
            SoundList("drums_120_02.ogg"),
            SoundList("drums_120_03.ogg"),
            SoundList("drums_120_04.ogg"),
            SoundList("drums_120_05.ogg"),
            SoundList("drums_120_06.ogg"),
            SoundList("drums_120_07.ogg"),
            SoundList("drums_120_08.ogg"),
            SoundList("drums_120_09.ogg"),
            SoundList("drums_120_10.ogg"),
            SoundList("drums_120_11.ogg"),
            SoundList("drums_120_12.ogg"),
            SoundList("drums_120_13.ogg"),
            SoundList("drums_120_14.ogg"),
            SoundList("drums_120_15.ogg"),
            SoundList("drums_120_16.ogg"),
            SoundList("drums_120_17.ogg"),
            SoundList("drums_120_18.ogg"),
            SoundList("drums_120_19.ogg"),
            SoundList("drums_120_20.ogg"),
            SoundList("drums_120_21.ogg")
        )
        pSoundList = arrayListOf(
            SoundList("guitar_85_01.ogg"),
            SoundList("guitar_85_02.ogg"),
            SoundList("guitar_85_03.ogg"),
            SoundList("guitar_85_04.ogg"),
            SoundList("guitar_85_05.ogg"),
            SoundList("guitar_85_06.ogg"),
            SoundList("guitar_85_07.ogg"),
            SoundList("guitar_85_08.ogg"),
            SoundList("guitar_85_09.ogg"),
            SoundList("guitar_85_10.ogg"),
            SoundList("guitar_85_11.ogg"),
            SoundList("guitar_85_12.ogg"),
            SoundList("guitar_85_13.ogg"),
            SoundList("guitar_90_01.ogg"),
            SoundList("guitar_90_02.ogg"),
            SoundList("guitar_90_03.ogg"),
            SoundList("guitar_90_04.ogg"),
            SoundList("guitar_90_05.ogg"),
            SoundList("guitar_90_06.ogg"),
            SoundList("guitar_90_07.ogg"),
            SoundList("guitar_90_08.ogg"),
            SoundList("guitar_90_09.ogg"),
            SoundList("guitar_90_10.ogg"),
            SoundList("guitar_90_11.ogg"),
            SoundList("guitar_90_12.ogg"),
            SoundList("guitar_90_13.ogg"),
            SoundList("guitar_90_14.ogg"),
            SoundList("guitar_90_15.ogg"),
            SoundList("guitar_90_16.ogg"),
            SoundList("guitar_90_17.ogg"),
            SoundList("guitar_90_18.ogg"),
            SoundList("guitar_90_19.ogg"),
            SoundList("guitar_90_20.ogg"),
            SoundList("guitar_90_21.ogg"),
            SoundList("guitar_95_01.ogg"),
            SoundList("guitar_95_02.ogg"),
            SoundList("guitar_95_03.ogg"),
            SoundList("guitar_95_04.ogg"),
            SoundList("guitar_95_05.ogg"),
            SoundList("guitar_95_06.ogg"),
            SoundList("guitar_95_07.ogg"),
            SoundList("guitar_95_08.ogg"),
            SoundList("guitar_100_01.ogg"),
            SoundList("guitar_100_02.ogg"),
            SoundList("guitar_100_03.ogg"),
            SoundList("guitar_100_04.ogg"),
            SoundList("guitar_100_05.ogg"),
            SoundList("guitar_100_06.ogg"),
            SoundList("guitar_110_01.ogg"),
            SoundList("guitar_110_02.ogg"),
            SoundList("guitar_110_03.ogg"),
            SoundList("guitar_110_04.ogg"),
            SoundList("guitar_110_05.ogg"),
            SoundList("guitar_110_06.ogg"),
            SoundList("guitar_110_07.ogg"),
            SoundList("guitar_110_08.ogg"),
            SoundList("guitar_110_09.ogg"),
            SoundList("guitar_110_10.ogg"),
            SoundList("guitar_110_11.ogg"),
            SoundList("guitar_120_01.ogg"),
            SoundList("guitar_120_02.ogg"),
            SoundList("guitar_120_03.ogg"),
            SoundList("guitar_120_04.ogg"),
            SoundList("guitar_120_05.ogg"),
            SoundList("guitar_120_06.ogg"),
            SoundList("guitar_120_07.ogg"),
            SoundList("guitar_120_08.ogg"),
            SoundList("guitar_120_09.ogg"),
            SoundList("guitar_120_10.ogg"),
            SoundList("guitar_120_11.ogg"),
            SoundList("guitar_120_12.ogg"),
            SoundList("guitar_120_13.ogg"),
            SoundList("guitar_120_14.ogg"),
            SoundList("guitar_120_15.ogg"),
            SoundList("guitar_120_16.ogg"),
            SoundList("guitar_120_17.ogg"),
            SoundList("guitar_120_18.ogg"),
            SoundList("guitar_120_19.ogg"),
            SoundList("guitar_120_20.ogg"),
            SoundList("guitar_120_21.ogg")
        )
        qSoundList = arrayListOf(
            SoundList("keys_85_01.ogg"),
            SoundList("keys_85_02.ogg"),
            SoundList("keys_90_01.ogg"),
            SoundList("keys_90_02.ogg"),
            SoundList("keys_90_03.ogg"),
            SoundList("keys_90_04.ogg"),
            SoundList("keys_90_05.ogg"),
            SoundList("keys_90_06.ogg"),
            SoundList("keys_90_07.ogg"),
            SoundList("keys_90_08.ogg"),
            SoundList("keys_95_01.ogg"),
            SoundList("keys_95_02.ogg"),
            SoundList("keys_95_03.ogg"),
            SoundList("keys_100_01.ogg"),
            SoundList("keys_100_02.ogg"),
            SoundList("keys_100_03.ogg"),
            SoundList("keys_100_04.ogg"),
            SoundList("keys_100_05.ogg"),
            SoundList("keys_110_01.ogg"),
            SoundList("keys_110_02.ogg"),
            SoundList("keys_110_03.ogg"),
            SoundList("keys_110_04.ogg"),
            SoundList("keys_110_05.ogg"),
            SoundList("keys_110_06.ogg"),
            SoundList("keys_120_01.ogg"),
            SoundList("keys_120_02.ogg"),
            SoundList("keys_120_03.ogg"),
            SoundList("keys_120_04.ogg"),
            SoundList("keys_120_05.ogg"),
            SoundList("keys_120_06.ogg")
        )
        rSoundList = arrayListOf(
            SoundList("orch_85_01.ogg"),
            SoundList("orch_95.ogg"),
            SoundList("orch_100_01.ogg"),
            SoundList("orch_100_02.ogg"),
            SoundList("orch_100_03.ogg"),
            SoundList("orch_110_01.ogg"),
            SoundList("orch_110_02.ogg")
        )
        baSoundList = arrayListOf(
            SoundList("percussion_85_01.ogg"),
            SoundList("percussion_85_02.ogg"),
            SoundList("percussion_85_03.ogg"),
            SoundList("percussion_85_04.ogg"),
            SoundList("percussion_90_01.ogg"),
            SoundList("percussion_90_02.ogg"),
            SoundList("percussion_90_03.ogg"),
            SoundList("percussion_90_04.ogg"),
            SoundList("percussion_90_05.ogg"),
            SoundList("percussion_90_06.ogg"),
            SoundList("percussion_90_07.ogg"),
            SoundList("percussion_90_08.ogg"),
            SoundList("percussion_90_09.ogg"),
            SoundList("percussion_90_10.ogg"),
            SoundList("percussion_95_01.ogg"),
            SoundList("percussion_95_02.ogg"),
            SoundList("percussion_95_03.ogg"),
            SoundList("percussion_100_01.ogg"),
            SoundList("percussion_100_02.ogg"),
            SoundList("percussion_100_03.ogg"),
            SoundList("percussion_100_04.ogg"),
            SoundList("percussion_100_05.ogg"),
            SoundList("percussion_100_06.ogg"),
            SoundList("percussion_100_07.ogg"),
            SoundList("percussion_100_08.ogg"),
            SoundList("percussion_100_09.ogg"),
            SoundList("percussion_100_10.ogg"),
            SoundList("percussion_100_11.ogg"),
            SoundList("percussion_100_12.ogg"),
            SoundList("percussion_110_01.ogg"),
            SoundList("percussion_110_02.ogg"),
            SoundList("percussion_110_03.ogg"),
            SoundList("percussion_110_04.ogg"),
            SoundList("percussion_110_05.ogg"),
            SoundList("percussion_110_06.ogg"),
            SoundList("percussion_110_07.ogg"),
            SoundList("percussion_110_08.ogg"),
            SoundList("percussion_110_09.ogg"),
            SoundList("percussion_110_10.ogg"),
            SoundList("percussion_110_11.ogg"),
            SoundList("percussion_110_12.ogg"),
            SoundList("percussion_110_13.ogg"),
            SoundList("percussion_110_14.ogg"),
            SoundList("percussion_110_15.ogg"),
            SoundList("percussion_120_01.ogg"),
            SoundList("percussion_120_02.ogg"),
            SoundList("percussion_120_03.ogg"),
            SoundList("percussion_120_04.ogg"),
            SoundList("percussion_120_05.ogg"),
            SoundList("percussion_120_06.ogg"),
            SoundList("percussion_120_07.ogg"),
            SoundList("percussion_120_08.ogg"),
            SoundList("percussion_120_09.ogg"),
            SoundList("percussion_120_10.ogg"),
            SoundList("percussion_120_11.ogg"),
            SoundList("percussion_120_12.ogg"),
            SoundList("percussion_120_13.ogg")
        )
        bbSoundList = arrayListOf(
            SoundList("synth_90_01.ogg"),
            SoundList("synth_90_02.ogg"),
            SoundList("synth_90_03.ogg"),
            SoundList("synth_90_04.ogg"),
            SoundList("synth_90_05.ogg"),
            SoundList("synth_90_06.ogg"),
            SoundList("synth_90_07.ogg"),
            SoundList("synth_95_01.ogg"),
            SoundList("synth_95_02.ogg"),
            SoundList("synth_100_01.ogg"),
            SoundList("synth_100_02.ogg"),
            SoundList("synth_100_03.ogg"),
            SoundList("synth_100_04.ogg"),
            SoundList("synth_100_05.ogg"),
            SoundList("synth_110_01.ogg"),
            SoundList("synth_110_02.ogg"),
            SoundList("synth_110_03.ogg"),
            SoundList("synth_110_04.ogg"),
            SoundList("synth_110_05.ogg"),
            SoundList("synth_110_06.ogg"),
            SoundList("synth_110_07.ogg"),
            SoundList("synth_120_01.ogg"),
            SoundList("synth_120_02.ogg"),
            SoundList("synth_120_03.ogg"),
            SoundList("synth_120_04.ogg")
        )
        sSoundList = arrayListOf()
        tSoundList = arrayListOf()

        val listView = findViewById<ListView>(R.id.list_view)

        aCustomAdapter = CustomAdapter(this, aSoundList, this)
        bCustomAdapter = CustomAdapter(this, bSoundList, this)
        cCustomAdapter = CustomAdapter(this, cSoundList, this)
        dCustomAdapter = CustomAdapter(this, dSoundList, this)
        eCustomAdapter = CustomAdapter(this, eSoundList, this)
        fCustomAdapter = CustomAdapter(this, fSoundList, this)
        gCustomAdapter = CustomAdapter(this, gSoundList, this)
        hCustomAdapter = CustomAdapter(this, hSoundList, this)
        iCustomAdapter = CustomAdapter(this, iSoundList, this)
        jCustomAdapter = CustomAdapter(this, jSoundList, this)
        kCustomAdapter = CustomAdapter(this, kSoundList, this)
        lCustomAdapter = CustomAdapter(this, lSoundList, this)
        mCustomAdapter = CustomAdapter(this, mSoundList, this)
        aaCustomAdapter = CustomAdapter(this, aSoundList, this)
        abCustomAdapter = CustomAdapter(this, aSoundList, this)
        acCustomAdapter = CustomAdapter(this, aSoundList, this)
        nCustomAdapter = CustomAdapter(this, nSoundList, this)
        oCustomAdapter = CustomAdapter(this, oSoundList, this)
        pCustomAdapter = CustomAdapter(this, pSoundList, this)
        qCustomAdapter = CustomAdapter(this, qSoundList, this)
        rCustomAdapter = CustomAdapter(this, rSoundList, this)
        baCustomAdapter = CustomAdapter(this, baSoundList, this)
        bbCustomAdapter = CustomAdapter(this, bbSoundList, this)
        sCustomAdapter = CustomAdapter(this, sSoundList, this)
        tCustomAdapter = CustomAdapter(this, tSoundList, this)

        listView.adapter = aCustomAdapter

        mp = MediaPlayer()

        supportActionBar?.title ="guitar_85_01"


            val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                sSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()


        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item)

        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown)



        meSpinner.adapter = adapter3


        meSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!meSpinner.isFocusable) {
                    meSpinner.isFocusable = true
                    return
                }

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        radioButton19.performClick()
                        soundListView.adapter = aCustomAdapter
                        aCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioButton19.performClick()
                        soundListView.adapter = bCustomAdapter
                        bCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        radioButton19.performClick()
                        soundListView.adapter = cCustomAdapter
                        cCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        radioButton19.performClick()
                        soundListView.adapter = dCustomAdapter
                        dCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        radioButton19.performClick()
                        soundListView.adapter = eCustomAdapter
                        eCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        radioButton19.performClick()
                        soundListView.adapter = fCustomAdapter
                        fCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        radioButton19.performClick()
                        soundListView.adapter = gCustomAdapter
                        gCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        radioButton19.performClick()
                        soundListView.adapter = hCustomAdapter
                        hCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    8 -> {
                        radioButton19.performClick()
                        soundListView.adapter = iCustomAdapter
                        iCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    9 -> {
                        radioButton19.performClick()
                        soundListView.adapter = jCustomAdapter
                        jCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    10 -> {
                        radioButton19.performClick()
                        soundListView.adapter = kCustomAdapter
                        kCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    11 -> {
                        radioButton19.performClick()
                        soundListView.adapter = lCustomAdapter
                        lCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    12 -> {
                        radioButton19.performClick()
                        soundListView.adapter = mCustomAdapter
                        mCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    13 -> {
                        radioButton19.performClick()
                        soundListView.adapter = mCustomAdapter
                        aaCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    14 -> {
                        radioButton19.performClick()
                        soundListView.adapter = mCustomAdapter
                        abCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    15 -> {
                        radioButton19.performClick()
                        soundListView.adapter = mCustomAdapter
                        acCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    16 -> {
                        radioButton18.performClick()
                        soundListView.adapter = sCustomAdapter
                        sCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    17 -> {
                        selectEX()
                        radioButton18.performClick()
                        soundListView.adapter = tCustomAdapter
                        tCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    18 -> selectAudio()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        meSpinner.isFocusable = false


        val audioAttributes = AudioAttributes.Builder()

                .setUsage(AudioAttributes.USAGE_GAME)

                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

        soundPool = SoundPool.Builder()

                .setAudioAttributes(audioAttributes)

                .setMaxStreams(20)
                .build()

        sound1 = soundPool.load(assets.openFd("cycdh_trasha_07.ogg"), 1)

        sound2 = soundPool.load(assets.openFd("cycdh_multicrashlo_01.ogg"), 1)

        sound3 = soundPool.load(assets.openFd("cycdh_crash_01.ogg"), 1)

        sound4 = soundPool.load(assets.openFd("khats_clsd_02.ogg"), 1)

        sound5 = soundPool.load(assets.openFd("cycdh_ludflama_04.ogg"), 1)

        sound6 = soundPool.load(assets.openFd("cycdh_sab_hfhat_02.ogg"), 1)

        sound7 = soundPool.load(assets.openFd("pearlpiccolo_side_snron_01.ogg"), 1)

        sound8 = soundPool.load(assets.openFd("cycdh_ludrimc_03.ogg"), 1)

        sound9 = soundPool.load(assets.openFd("cycdh_ludflamb_02.ogg"), 1)

        sound10 = soundPool.load(assets.openFd("cycdh_ludrima_01.ogg"), 1)

        sound11 = soundPool.load(assets.openFd("cycdh_acoukick_12.ogg"), 1)

        sound12 = soundPool.load(assets.openFd("acoustic_snare_03.ogg"), 1)

        sound13 = soundPool.load(assets.openFd("cycdh_loosekick_04.ogg"), 1)

        sound14 = soundPool.load(assets.openFd("cycdh_keskick_08.ogg"), 1)

        sound15 = soundPool.load(assets.openFd("cycdh_loosekick_07.ogg"), 1)

        lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.guitar_85_01))


        imageView.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView2.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView3.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView4.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView5.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView6.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView7.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView8.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView9.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false

        }

        imageView10.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView11.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView12.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView13.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView14.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView15.setOnTouchListener { _, event ->
            if (listView.isVisible) {
                listView.visibility = View.INVISIBLE
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound15, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }


        imageView.setOnLongClickListener {
            radioButton.performClick()
            meSpinner.performClick()
            true
        }
        imageView2.setOnLongClickListener {
            radioButton2.performClick()
            meSpinner.performClick()
            true
        }
        imageView3.setOnLongClickListener {
            radioButton3.performClick()
            meSpinner.performClick()
            true
        }
        imageView4.setOnLongClickListener {
            radioButton4.performClick()
            meSpinner.performClick()
            true
        }
        imageView5.setOnLongClickListener {
            radioButton5.performClick()
            meSpinner.performClick()
            true
        }
        imageView6.setOnLongClickListener {
            radioButton6.performClick()
            meSpinner.performClick()
            true
        }
        imageView7.setOnLongClickListener {
            radioButton7.performClick()
            meSpinner.performClick()
            true
        }
        imageView8.setOnLongClickListener {
            radioButton8.performClick()
            meSpinner.performClick()
            true
        }
        imageView9.setOnLongClickListener {
            radioButton9.performClick()
            meSpinner.performClick()
            true
        }
        imageView10.setOnLongClickListener {
            radioButton10.performClick()
            meSpinner.performClick()
            true
        }
        imageView11.setOnLongClickListener {
            radioButton11.performClick()
            meSpinner.performClick()
            true
        }
        imageView12.setOnLongClickListener {
            radioButton12.performClick()
            meSpinner.performClick()
            true
        }
        imageView13.setOnLongClickListener {
            radioButton13.performClick()
            meSpinner.performClick()
            true
        }
        imageView14.setOnLongClickListener {
            radioButton14.performClick()
            meSpinner.performClick()
            true
        }
        imageView15.setOnLongClickListener {
            radioButton15.performClick()
            meSpinner.performClick()
            true
        }
    }

    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density
            var adWidthPixels = adViewContainer.width.toFloat()
            if (adWidthPixels == 0.0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()


            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this@MainActivity, adWidth)
        }

    private fun initAdMob() {
        adViewContainer = findViewById(R.id.adView)

        MobileAds.initialize(this) {}
        admobmAdView = AdView(this)
        admobmAdView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        admobmAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adViewContainer.addView(admobmAdView)
            }
        }
    }

    private fun loadAdMob() {
        val request = AdRequest.Builder().build()
        admobmAdView.adSize = adSize
        admobmAdView.loadAd(request)
    }

    override fun clicked(soundList: SoundList) {
        sound16 = if (radioButton18.isChecked) {
            soundPool.load(soundList.name, 1)
        } else {
            soundPool.load(assets.openFd(soundList.name), 1)
        }
            soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f)
            }
    }

    override fun clicked2(soundList: SoundList) {
        try {
        when {
            radioButton.isChecked && radioButton18.isChecked -> {
                imageView.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound1 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton2.isChecked && radioButton18.isChecked -> {
                imageView2.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView2.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound2 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView2.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton3.isChecked && radioButton18.isChecked -> {
                imageView3.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView3.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound3 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView3.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton4.isChecked && radioButton18.isChecked -> {
                imageView4.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView4.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound4 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView4.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton5.isChecked && radioButton18.isChecked -> {
                imageView5.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView5.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound5 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView5.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton6.isChecked && radioButton18.isChecked -> {
                imageView6.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView6.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound6 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView6.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton7.isChecked && radioButton18.isChecked -> {
                imageView7.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView7.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound7 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView7.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton8.isChecked && radioButton18.isChecked -> {
                imageView8.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView8.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound8 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView8.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton9.isChecked && radioButton18.isChecked -> {
                imageView9.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView9.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound9 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView9.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton10.isChecked && radioButton18.isChecked -> {
                imageView10.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView10.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound10 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView10.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton11.isChecked && radioButton18.isChecked -> {
                imageView11.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView11.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound11 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView11.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton12.isChecked && radioButton18.isChecked -> {
                imageView12.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView12.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound12 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView12.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton13.isChecked && radioButton18.isChecked -> {
                imageView13.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView13.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound13 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView13.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton14.isChecked && radioButton18.isChecked -> {
                imageView14.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView14.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound14 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView14.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton15.isChecked && radioButton18.isChecked -> {
                imageView15.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView15.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound15 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView15.text = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
            }
            radioButton16.isChecked && radioButton18.isChecked -> {
                lmp.release()
                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(soundList.name))
                supportActionBar?.title = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
            }
            radioButton.isChecked && radioButton19.isChecked -> {
                imageView.setColorFilter(Color.parseColor("#5A5A66"))
                handler.postDelayed({ imageView.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound1 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton2.isChecked && radioButton19.isChecked -> {
                imageView2.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView2.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound2 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView2.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton3.isChecked && radioButton19.isChecked -> {
                imageView3.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView3.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound3 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView3.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton4.isChecked && radioButton19.isChecked -> {
                imageView4.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView4.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound4 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView4.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton5.isChecked && radioButton19.isChecked -> {
                imageView5.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView5.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound5 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView5.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton6.isChecked && radioButton19.isChecked -> {
                imageView6.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView6.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound6 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView6.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton7.isChecked && radioButton19.isChecked -> {
                imageView7.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView7.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound7 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView7.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton8.isChecked && radioButton19.isChecked -> {
                imageView8.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView8.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound8 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView8.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton9.isChecked && radioButton19.isChecked -> {
                imageView9.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView9.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound9 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView9.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton10.isChecked && radioButton19.isChecked -> {
                imageView10.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView10.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound10 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView10.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton11.isChecked && radioButton19.isChecked -> {
                imageView11.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView11.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound11 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView11.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton12.isChecked && radioButton19.isChecked -> {
                imageView12.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView12.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound12 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView12.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton13.isChecked && radioButton19.isChecked -> {
                imageView13.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView13.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound13 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView13.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton14.isChecked && radioButton19.isChecked -> {
                imageView14.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView14.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound14 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView14.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton15.isChecked && radioButton19.isChecked -> {
                imageView15.setColorFilter(Color.parseColor("#FDF5E6"))
                handler.postDelayed({ imageView15.setColorFilter(Color.parseColor("#EC9F05")) }, 1000)
                sound15 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView15.text = soundList.name.replaceAfterLast(".", "").replace(".", "")
            }
            radioButton16.isChecked -> {
                lmp.release()
                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse("android.resource://" + packageName + "/raw/" + soundList.name.replace(".ogg", "")))
                supportActionBar?.title = soundList.name.replaceAfterLast(".", "").replace(".", "")
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
            }
            radioButton17.isChecked -> {
                audioName = soundList.name
                button4.performClick()
            }
        }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_LONG).show()
        }
        findViewById<ListView>(R.id.list_view).visibility = View.INVISIBLE
    }

    private fun startCapturing() {
        if (!isRecordAudioPermissionGranted()) {
            requestRecordAudioPermission()
        } else {
            startMediaProjectionRequest()
        }
    }

    private fun stopCapturing() {

        startService(Intent(this, AudioCaptureService::class.java).apply {
            action = AudioCaptureService.ACTION_STOP
        })
        menuSwitch0 = true
        switch0.isChecked = false
        invalidateOptionsMenu()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#5A5A66")))
    }

    private fun isRecordAudioPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    private fun isReadExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun startMediaProjectionRequest() {
        mediaProjectionManager =
                applicationContext.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(
                mediaProjectionManager.createScreenCaptureIntent(),
                MEDIA_PROJECTION_REQUEST_CODE
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == MEDIA_PROJECTION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                menuSwitch0 = false
                switch0.isChecked = true
                invalidateOptionsMenu()
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#EC7357")))
                Toast.makeText(
                        this,
                        R.string.onActivityResult1,
                        Toast.LENGTH_SHORT
                ).show()

                val audioCaptureIntent = Intent(this, AudioCaptureService::class.java).apply {
                    action = AudioCaptureService.ACTION_START
                    putExtra(AudioCaptureService.EXTRA_RESULT_DATA, resultData!!)
                }
                startForegroundService(audioCaptureIntent)
            } else {
                Toast.makeText(
                        this,
                        R.string.onActivityResult2,
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {

            READ_REQUEST_CODE2 -> {

                resultData?.data?.also { uri ->
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        val item = "/storage/emulated/0/" + split[1]
                        when {
                            radioButton.isChecked -> {
                                sound1 = soundPool.load(item, 1)
                                textView.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton2.isChecked -> {
                                sound2 = soundPool.load(item, 1)
                                textView2.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton3.isChecked -> {
                                sound3 = soundPool.load(item, 1)
                                textView3.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton4.isChecked -> {
                                sound4 = soundPool.load(item, 1)
                                textView4.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton5.isChecked -> {
                                sound5 = soundPool.load(item, 1)
                                textView5.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton6.isChecked -> {
                                sound6 = soundPool.load(item, 1)
                                textView6.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton7.isChecked -> {
                                sound7 = soundPool.load(item, 1)
                                textView7.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton8.isChecked -> {
                                sound8 = soundPool.load(item, 1)
                                textView8.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton9.isChecked -> {
                                sound9 = soundPool.load(item, 1)
                                textView9.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton10.isChecked -> {
                                sound10 = soundPool.load(item, 1)
                                textView10.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton11.isChecked -> {
                                sound11 = soundPool.load(item, 1)
                                textView11.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton12.isChecked -> {
                                sound12 = soundPool.load(item, 1)
                                textView12.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton13.isChecked -> {
                                sound13 = soundPool.load(item, 1)
                                textView13.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton14.isChecked -> {
                                sound14 = soundPool.load(item, 1)
                                textView14.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton15.isChecked -> {
                                sound15 = soundPool.load(item, 1)
                                textView15.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton16.isChecked -> {
                                lmp.release()
                                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item))
                                supportActionBar?.title = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                            }
                            radioButton17.isChecked -> {
                                audioName = item
                                button4.performClick()
                            }
                        }
                    } else {
                        try {
                            val item2 = "/stroage/" + type + "/" + split[1]
                            when {
                                radioButton.isChecked -> {
                                    sound1 = soundPool.load(item2, 1)
                                    textView.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton2.isChecked -> {
                                    sound2 = soundPool.load(item2, 1)
                                    textView2.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton3.isChecked -> {
                                    sound3 = soundPool.load(item2, 1)
                                    textView3.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton4.isChecked -> {
                                    sound4 = soundPool.load(item2, 1)
                                    textView4.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton5.isChecked -> {
                                    sound5 = soundPool.load(item2, 1)
                                    textView5.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton6.isChecked -> {
                                    sound6 = soundPool.load(item2, 1)
                                    textView6.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton7.isChecked -> {
                                    sound7 = soundPool.load(item2, 1)
                                    textView7.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton8.isChecked -> {
                                    sound8 = soundPool.load(item2, 1)
                                    textView8.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton9.isChecked -> {
                                    sound9 = soundPool.load(item2, 1)
                                    textView9.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton10.isChecked -> {
                                    sound10 = soundPool.load(item2, 1)
                                    textView10.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton11.isChecked -> {
                                    sound11 = soundPool.load(item2, 1)
                                    textView11.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton12.isChecked -> {
                                    sound12 = soundPool.load(item2, 1)
                                    textView12.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton13.isChecked -> {
                                    sound13 = soundPool.load(item2, 1)
                                    textView13.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton14.isChecked -> {
                                    sound14 = soundPool.load(item2, 1)
                                    textView14.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton15.isChecked -> {
                                    sound15 = soundPool.load(item2, 1)
                                    textView15.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton16.isChecked -> {
                                    lmp.release()
                                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item2))
                                    supportActionBar?.title = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace(".", "")
                                }
                                radioButton17.isChecked -> {
                                    audioName = item2
                                    button4.performClick()
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, R.string.READ_REQUEST_CODE2, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun selectCh() {

        val chSpinner = findViewById<Spinner>(R.id.choose_loop_spinner)

        val adapterC = ArrayAdapter.createFromResource(this, R.array.spinnerItems2, android.R.layout.simple_spinner_item)

        adapterC.setDropDownViewResource(R.layout.custom_spinner_dropdown)


        chSpinner.adapter = adapterC

        chSpinner.performClick()


        chSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!chSpinner.isFocusable) {
                    chSpinner.isFocusable = true
                    return
                }

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = nCustomAdapter
                        nCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = oCustomAdapter
                        oCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = pCustomAdapter
                        pCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = qCustomAdapter
                        qCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = rCustomAdapter
                        rCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = baCustomAdapter
                        baCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton19.performClick()
                        soundListView.adapter = bbCustomAdapter
                        bbCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton18.performClick()
                        soundListView.adapter = sCustomAdapter
                        sCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    8 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        radioButton18.performClick()
                        selectEX()
                        soundListView.adapter = tCustomAdapter
                        tCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    9 -> {
                        radioButton16.performClick()
                        selectAudio()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        chSpinner.isFocusable = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuLamp = menu!!.findItem(R.id.menu1)
        if (menuSwitch) {
            menuLamp.setIcon(R.drawable.ic_baseline_play_arrow_24)
        } else {
            menuLamp.setIcon(R.drawable.ic_baseline_stop_24)
        }

        val menuLamp3 = menu.findItem(R.id.menu8)
        if (menuSwitch0) {
            menuLamp3.setIcon(R.drawable.ic_baseline_radio_button_checked_24_2)
        } else {
            menuLamp3.setIcon(R.drawable.ic_baseline_radio_button_checked_24)
        }

        return true
    }

    private var menuSwitch = true
    private var menuSwitch2 = true
    private var menuSwitch0 = true
    private var mediaRecorder = MediaRecorder()

    private val locale: Locale = Locale.getDefault()


    @SuppressLint("SimpleDateFormat")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val timeStamp: String = SimpleDateFormat("MM月dd日HH時mm分ss秒").format(Date())
        val soundFilePathJA = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString() + "/$timeStamp" + "の録音.ogg"

        val timestamp2: String = SimpleDateFormat("dd-MM-yyyy-hh-mm-ss", Locale.US).format(Date())
        val soundFilePathEN = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString() + "/Record-$timestamp2.ogg"

        fun startRecording() {
            try {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
                if (locale == Locale.JAPAN) {
                    mediaRecorder.setOutputFile(soundFilePathJA)
                } else {
                    mediaRecorder.setOutputFile(soundFilePathEN)
                }
                mediaRecorder.setMaxDuration(180000)
                mediaRecorder.setOnInfoListener { _, what, _ ->
                    if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                        mediaRecorder.stop()
                        menuSwitch2 = true
                        invalidateOptionsMenu()
                        switch2.isChecked = false
                        Toast.makeText(applicationContext, R.string.startRecording1, Toast.LENGTH_LONG).show()
                    }
                }
                mediaRecorder.prepare()
                mediaRecorder.start()
                Toast.makeText(applicationContext, R.string.startRecording2, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, R.string.startRecording3, Toast.LENGTH_LONG).show()

            }
        }

        fun stopRecording() {
            try {
                mediaRecorder.stop()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, R.string.stopRecording, Toast.LENGTH_LONG).show()
            }
        }

            button4.setOnClickListener {
                try {
                val myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    .toString() + "/showwavespics.png"
                FFmpeg.execute("-i $audioName -filter_complex showwavespic=s=2560x1280:colors=blue:scale=0 -y $myDir")

                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.custom_dialog, null)

                mp.release()
                mp = MediaPlayer()
                mp.setDataSource(this, Uri.parse(audioName))
                mp.prepare()

                val seekBar = dialogView.findViewById<SeekBar>(R.id.seekBar)
                val seekBar2 = dialogView.findViewById<SeekBar>(R.id.seekBar2)

                seekBar.progress = 0
                seekBar2.progress = 0

                seekBar.max = mp.duration

                seekBar2.max = mp.duration
                seekBar2.progress = mp.duration

                start = 0
                stop = mp.duration

                val text1 = dialogView.findViewById<TextView>(R.id.textView16)
                val text2 = dialogView.findViewById<TextView>(R.id.textView17)
                val text3 = dialogView.findViewById<TextView>(R.id.textView18)

                text1.text = SimpleDateFormat("mm:ss.SSS").format(Date(0.toLong())).toString()
                text2.text =
                    SimpleDateFormat("mm:ss.SSS").format(Date(mp.duration.toLong())).toString()
                text3.text = audioName.replaceBeforeLast("/", "").replace("/", "")

                seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        text1.text = SimpleDateFormat("mm:ss.SSS").format(Date(progress.toLong()))
                        start = progress

                    }


                    override fun onStartTrackingTouch(seekBar: SeekBar?) {

                    }


                    override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    }
                })

                seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        text2.text = SimpleDateFormat("mm:ss.SSS").format(Date(progress.toLong()))
                        stop = progress
                    }


                    override fun onStartTrackingTouch(seekBar: SeekBar?) {

                    }


                    override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    }
                })


                val image = dialogView.findViewById<View>(R.id.imageView16) as ImageView

                image.setImageURI(Uri.parse(myDir))

                val button = dialogView.findViewById(R.id.button) as Button
                val button2 = dialogView.findViewById(R.id.button2) as Button
                val button3 = dialogView.findViewById(R.id.button3) as Button

                button.setOnClickListener {
                    when {
                        start < stop -> {
                            val builder2 = AlertDialog.Builder(this)
                            val inflater2 = layoutInflater
                            val dialogView2 = inflater2.inflate(R.layout.file_name, null)
                            builder2.setView(dialogView2)
                                .setTitle(R.string.button_setOnClickListener1)
                                .setPositiveButton(R.string.button_setOnClickListener2) { _, _ ->
                                    val nt = dialogView2.findViewById<EditText>(R.id.filename)
                                    val fnt = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
                                        .toString() + "/" + nt.text.replace("/".toRegex(),
                                        "") + audioName.replaceBeforeLast(".", "")
                                    try {
                                        FFmpeg.execute("-ss ${text1.text} -to ${text2.text} -i $audioName -y $fnt")
                                        button3.performClick()
                                        Toast.makeText(applicationContext,
                                            R.string.button_setOnClickListener3,
                                            Toast.LENGTH_LONG).show()
                                    } catch (e: Exception) {
                                        Toast.makeText(applicationContext,
                                            R.string.button_setOnClickListener4,
                                            Toast.LENGTH_LONG).show()
                                    }
                                }
                                .setNegativeButton(R.string.button_setOnClickListener5) { _, _ ->

                                }
                                .show()

                        }
                        start > stop -> Toast.makeText(applicationContext,
                            R.string.button_setOnClickListener6,
                            Toast.LENGTH_LONG).show()
                        start == stop -> Toast.makeText(applicationContext,
                            R.string.button_setOnClickListener6,
                            Toast.LENGTH_LONG).show()
                    }
                }

                button2.setOnClickListener {
                    if (switch3.isChecked) {
                        mp.stop()
                        mp.prepare()
                        handler.removeCallbacksAndMessages(null)
                        switch3.isChecked = false
                    } else {
                        mp.seekTo(start)
                        mp.start()
                        switch3.isChecked = true
                        if (mp.isPlaying)
                            handler.postDelayed({
                                mp.stop()
                                mp.prepare()
                                switch3.isChecked = false
                            }, (stop - start).toLong())
                    }
                }



                builder.setView(dialogView)
                    .setOnCancelListener {
                        mp.stop()
                        mp.prepare()
                        switch3.isChecked = false
                    }
                val dialog = builder.create()
                dialog.show()

                button3.setOnClickListener {
                    dialog.cancel()
                }
            } catch (e: Exception) {
                    Toast.makeText(applicationContext,
                        R.string.error,
                        Toast.LENGTH_LONG).show()
                }

            }

        val soundListView = findViewById<ListView>(R.id.list_view)

        when (item.itemId) {

            R.id.menu1 -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                if (switch1.isChecked) {
                    lmp.stop()
                    soundPool.autoPause()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1.isChecked = false
                } else {
                    lmp.start()
                    menuSwitch = false
                    invalidateOptionsMenu()
                    switch1.isChecked = true
                }
                return true
            }

            R.id.menu1a -> {
                radioButton17.performClick()
                selectAudio()
                return true
            }

            R.id.menu6 -> {
                AlertDialog.Builder(this)
                        .setTitle(R.string.menu6)
                        .setPositiveButton("YES") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("NO") { _, _ ->

                        }
                        .show()

                return true
            }

            R.id.menu8 -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                when {

                    Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> Toast.makeText(applicationContext, R.string.menu8, Toast.LENGTH_LONG).show()

                    switch0.isChecked -> stopCapturing()

                    else -> startCapturing()

                }

                return true
            }

            R.id.menuPlus -> {
                lmp.volumePlus()
                return true
            }

            R.id.menuMinus -> {
                lmp.volumeMinus()
                return true
            }

            R.id.menu9 -> {
                radioButton17.performClick()
                radioButton18.performClick()
                selectEX()
                soundListView.adapter = tCustomAdapter
                tCustomAdapter.notifyDataSetChanged()
                soundListView.visibility = View.VISIBLE
                return true
            }

            R.id.menu9b -> {
                if (!isRecordAudioPermissionGranted()) {
                    requestRecordAudioPermission()
                } else {
                    if (switch0.isChecked) {
                        stopCapturing()
                    }
                    val builder3 = AlertDialog.Builder(this)
                    val inflater3 = layoutInflater
                    val dialogView3 = inflater3.inflate(R.layout.record_dialog, null)
                    val rec = dialogView3.findViewById<ImageView>(R.id.imageView17)
                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                    rec.setOnClickListener {
                        if (switch2.isChecked) {
                            menuSwitch2 = true
                            stopRecording()
                            Toast.makeText(applicationContext, R.string.button_setOnClickListener3, Toast.LENGTH_LONG).show()
                            rec.setImageResource(R.drawable.ic_baseline_mic_24)
                            switch2.isChecked = false
                        } else {
                            menuSwitch2 = false
                            startRecording()
                            rec.setImageResource(R.drawable.ic_baseline_mic_24_2)
                            switch2.isChecked = true
                        }
                    }
                    builder3.setView(dialogView3)
                            .setTitle(R.string.builder3)
                            .setNegativeButton(R.string.back) { _, _ ->
                                if (switch2.isChecked) {
                                    menuSwitch2 = true
                                    stopRecording()
                                    Toast.makeText(applicationContext, R.string.button_setOnClickListener3, Toast.LENGTH_LONG).show()
                                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                                    switch2.isChecked = false
                                }
                            }
                            .setOnCancelListener {
                                if (switch2.isChecked) {
                                    menuSwitch2 = true
                                    stopRecording()
                                    Toast.makeText(applicationContext, R.string.button_setOnClickListener3, Toast.LENGTH_LONG).show()
                                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                                    switch2.isChecked = false
                                }
                            }
                    val dialog = builder3.create()
                    dialog.show()

                }
                return true
            }

            R.id.menu10 -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                selectCh()
                return true
            }

            R.id.action_settings -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        lmp.reset()
        lmp.release()
        mp.reset()
        mp.release()
        soundPool.autoPause()
        soundPool.release()

        mediaRecorder.reset()
        mediaRecorder.release()
        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1.isChecked = false
        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
            switch3.isChecked = false
        }
        if (!menuSwitch2) {
            menuSwitch2 = true
            invalidateOptionsMenu()
            mediaRecorder.stop()
            Toast.makeText(applicationContext, R.string.button_setOnClickListener3, Toast.LENGTH_LONG).show()
            switch2.isChecked = false
        }

            lmp.stop()
            soundPool.autoPause()

        super.onPause()
    }
}
