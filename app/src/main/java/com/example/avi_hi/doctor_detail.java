package com.example.avi_hi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.avi_hi.Login_Activity.hospital_login;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class doctor_detail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    doctor doctor;
    FirebaseAuth mAuth;
    Button sub;
    Spinner department,type,spec;
    String stitem,diitem;
    int st,dt;
    String citem,sitem,spitem;
    int c,s,sp;
    EditText doc_name,doc_phn,doc_degree, doc_aadhar,hos_name,hos_add,doc_add,doc_pin;

    Spinner state,district;

    ArrayList<String> states = new ArrayList<>(Arrays.asList(

            "आंध्र प्रदेश", "अरुणाचल प्रदेश","असम", "बिहार", "गोवा", "गुजरात", "हरियाणा",
            "हिमाचल प्रदेश", "जम्मू और कश्मीर", "कर्नाटका", "केरला",  "मध्य प्रदेश", "महाराष्ट्र", "मणिपुर",
            "मेघालय", "मिजोरम", "नागालैंड", " उड़ीसा","उत्तर प्रदेश","उत्तराखंड", "पंजाब", "राजस्थान", "सिक्किम",
            "तमिलनाडु", "त्रिपुरा","पश्चिम बंगाल", "छत्तीसगढ़",  "झारखंड", "तेलंगाना", "दिल्ली", "अंडमान और निकोबार",
            "दादरा और नगर हवेली", "दमन और दीव", "लक्षद्वीप – कवरेटी","पुदुच्चेरी", "लद्दाख"
    ));
    ArrayList<ArrayList<String> > districts = new ArrayList<>(Arrays.asList(

            new ArrayList<>(Arrays.asList(
                    "अनंतपुर", "चित्तूर", "पूर्व गोदावरी", "गुंटूर", "कड़प्पा", "कृष्णा", "कुर्नूल", "नेल्लौर", "प्रकाशम",
                    "श्रीकाकुलम", "विशाखापट्टनम", "विजयनगरम", "पश्चिम गोदावरी"
            )),
            new ArrayList<>(Arrays.asList(
                    "अंजॉ", "चेंगलॉन्ग", "पूर्वकमेंग", "पूर्वसियांग", "कुरुंगकुमे", "लोहित", "निचलीदिबांगघाटीजि", "निचलीसुबनसिरी",
                    "पपुमपारे", "तवांग", "तिरप", "उपरीदिबांगघाटी", "उपरीसुबनसिरी", "उपरीसियांग", "पश्चिमसियांग", "पश्चिमकमेंग"
            )),
            new ArrayList<>(Arrays.asList(
                    "तिनसुकिया", "डिब्रूगढ़", "धेमाजी", "चरैदेव", "शिवसागर", "लखीमपुर", "माजुली", "जोरहाट", "बिश्वनाथ", "गोलघाट",
                    "करगंज एंगलोंग ईस्ट", "सोनितपुर", "नागांव", "होजई", "कार्बी आंग्लोंग पश्चिम", "दीमा हसाओ", "कछार", "हैलाकांडी", "करीमगंज","मोरीगांव",
                    "उदलगुरी", "दरंग", "कामरूप महानगर", "बक्सा","नलबाड़ी","कामरूप","बारपेटा","चिरांग","बोंगाईगाँव","गोलपारा"," कोकराझार","धुबरी","दक्षिण सालारा-मनकाचर"
            )),
            new ArrayList<>(Arrays.asList(
                    "अरवल", "औरंगाबाद", "बांका", "बेगूसराय", "भागलपुर","भोजपुर", "बक्सर", "दरभंगा", "पूर्वी", "चंपारण","गया", "गोपालगंज","जमुई",
                    "जहानाबाद","कटिहार"," खगरिया","किशनगंज"," लखीसराय","मधेपुरा","मधुबनी","मुंगेर","मुजफ्फरपुर","नालंदा","नवादा","पटना", "पूर्णियाँ",
                    "सहरसा", "समस्तीपुर", "सारण", "रोहतास", "शेखपुरा", "शिवहर", "सीतामढ़ी", "सुपौल", "वैशाली", "पश्चिमी", "चंपारण"
            )),
            new ArrayList<>(Arrays.asList(
                    "चपोरा", "डाबोलिम", "मडगांव", "मर्मुगाओ (मर्मगाओ)", "पणजी पोर्ट","पंजिम", "पेलेट प्लांट जेट्टी / शिरोडा", "तलपोना"
            )),
            new ArrayList<>(Arrays.asList(
                    "अहमदाबाद", "अमरेली", "आणंद", "अरावली", "बनासकांठा", "भरुच", "भावनगर", "बोटाड", "छोटा उदयपुर", "दाहोद", "डांग",
                    "देवभूमि द्वारका", "गांधीनगर", "गिर सोमनाथ", "जामनगर", "जूनागढ़", "कच्छ", "खेड़ा", "महीसागर", "मेहसाणा", "मोरबी", "नर्मदा",
                    "नवसारी", "पंचमहल", "पाटन", "पोरबंदर", "राजकोट", "साबरकांठा", "सूरत", "सुरेंद्रनगर", "तापी", "वड़ोदरा", "वलसाड"
            )),
            new ArrayList<>(Arrays.asList(
                    "अम्बाला", "भिवानी", "फरीदाबाद", "फ़तेहाबाद", "गुड़गाँव", "हिसार", "झज्जर", "जिंद", "कैथल", "करनाल", "कुरुक्षेत्र", "महेन्द्रगढ", "नूँह",
                    "पलवल", "पंचकुला", "पानीपत", "रेवाड़ी", "रोहतक", "सिरसा", "सोनीपत", "यमुनानगर", "चरखीदादरी"
            )),
            new ArrayList<>(Arrays.asList(
                    "चम्बा", "कांगड़ा", "लाहौलऔरस्पीति", "कुल्लू", "मण्डी", "हमीरपुर", "ऊना", "बिलासपुर", "सोलन", "सिरमौर", "शिमला", "किन्नौर"
            )),
            new ArrayList<>(Arrays.asList(
                    "जम्मू", "डोडा", "किश्तवार", "राजौरी", "रियासी", "उधमपुर", "रामबन", "कठुआ", "साम्बा", "पुंछ", "श्रीनगर", "अनंतनाग", "कुलगाम",
                    "पुलवामा", "शोपियां", "बडगाम", "गांदरबल", "बांदीपोरा", "बारामुला", "कुपवाड़ा"
            )),
            new ArrayList<>(Arrays.asList(
                    "बागलकोट", "बैंगलोर", "बैंगलोर ग्रामीण", "बेलगाम", "बेल्लारी", "बीदर", "विजयपुरा", "चामराजनगर", "चिकबल्लापुर", "चिकमगलूर", "चित्रदुर्ग",
                    "दक्षिण कन्नड़", "दावणगेरे", "धारवाड़", "गदग", "गुलबर्गा", "हसन", "हावेरी", "कोडागू", "कोलार", "कोप्पल", "मांड्या", "मैसूर", "रायचूर",
                    "रामनगर", "शिमोगा", "तुमकुर", "उडुपी", "उत्तर कन्नड़", "यादगिर", "वड़ोदरा", "वलसाड"
            )),
            new ArrayList<>(Arrays.asList(
                    "कलपेट्टा", "इडुक्की", "पथानामथिट्टा", "कासरगोड", "कोट्टायम", "अलाप्पुझा", "कन्नूर", "कोल्लम", "पलक्कड़", "कोझिकोड", "त्रिशूर", "एर्नाकुलम", "थिरुवनंथपुरम", "मल्लापुरम"
            )),
            new ArrayList<>(Arrays.asList(
                    "जबलपुर", "कटनी", "भोपाल", "सीधी", "बड़वानी", "ग्वालियर", "धार", "उज्जैन", "हरदा", "शिवपुरी", "मंदसौर", "रायसेन", "शाजापुर",
                    "विदिशा", "श्योपुर", "अशोक नगर", "सतना", "गुना", "खंडवा (पूर्व निमाड़)", "डिंडोरी", "होशंगाबाद", "सिंगरोली", "उमरिया", "रतलाम", "राजगढ़",
                    "देवास", "छतरपुर", "अलिराजपुर", "बुरहानपुर", "भींड", "सागर", "नीमच", "दतिया", "पन्ना", "सिहोर", "खरगोन (पश्चिम निमाड़)", "रीवा",
                    "सिवनी", "शहडोल", "दमोह", "टीकमगढ़", "झाबुआ", "इंदौर", "मुरैना", "नरसिंहपुर", "बालाघाट", "मांडला", "छिंदवाड़ा", "बेतूल", "अनूपपुर", "आगर", "निवारी जिला"
            )),
            new ArrayList<>(Arrays.asList(
                    "अहमदनगर", "अकोला", "अमरावती", "औरंगाबाद", "बीड", "भंडारा", "बुलढाणा", "चंद्रपुर", "धुले", "गढ़चिरौली", "गोंदिया", "हिंगोली", "जलगांव",
                    "जालना", "कोल्हापुर", "लातूर", "मुंबई शहर", "मुंबई उपनगर", "नागपुर", "नांदेड", "नंदुरबार", "नासिक", "उस्मानाबाद", "परभणी", "पालघर", "पुणे",
                    "रायगढ़", "रत्नागिरि", "सांगली", "सतारा", "सिंधुदुर्ग", "सोलापुर", "ठाणे", "वर्धा", "वाशिम", "यवतमाल"
            )),
            new ArrayList<>(Arrays.asList(
                    "बिष्णुपुर", "छुरछंदपुर", "चंदेल", "इंफाल ईस्ट", "सेनापति", "तमेंगलोंग", "थौबल", "उखरूल", "इम्फाल वेस्ट", "कक्किंग", "जिरीबाम", "कमजोंग", "नोनी", "फेरजॉल", "टेंगनूपाल", "कांगपोकपि"
            )),
            new ArrayList<>(Arrays.asList(
                    "पूर्वी खासी हिल्स", "दक्षिण गारो हिल्स", "वेस्ट जयंतिया हिल्स", "पश्चिम खासी हिल्स", "पूर्वी गारो हिल्स", "री भोई", "दक्षिण पश्चिम खासी हिल्स",
                    "पूर्वी जयंतिया हिल्स", "उत्तरी गारो हिल्स", "दक्षिण पश्चिम गारो हिल्स", "वेस्ट गारो हिल्स"
            )),
            new ArrayList<>(Arrays.asList(
                    "आइजोल","कोलासिब","लॉन्गतलाई ","लुंगलेई","ममित " , "सइहा " , "सेरछिप" , "चम्फाई"
            )),
            new ArrayList<>(Arrays.asList(
                    "लोंगलेंग", "कोहिमा", "मोन", "तुएनसांग", "मोकोकचुंग", "वोखा", "फेक", "जुन्हेबोटो", "पेरेन", "कैफाइर", "दीमापुर"
            )),
            new ArrayList<>(Arrays.asList(
                    "अंगुल", "बलांगीर", "बालासोर", "बरगढ़ जिला ", "बौद्ध जिला", "भद्रक", "कटक", "देवगढ़", "ढेंकनाल", "गजपति", "गंजम",
                    "जगतसिंहपुर", "जाजपुर", "झारसुगुडा", "कालाहांडी", "कंधमाल", "केंद्रपाड़ा", "केंदुझार (क्योंझर)", "खोरधा", "कोरापुट", "मलकानगिरी",
                    "मयूरभंज", "नबरंगपुर", "नयागढ़", "नुआपाड़ा", "पुरी", "रायगडा", "संबलपुर", "सुबर्णपुर (सोनेपुर)", "सुंदरगढ़"
            )),
            new ArrayList<>(Arrays.asList(
                    "आगरा", "इलाहाबाद", "अलीगढ़ उत्तर प्रदेश", "अमेठी", "औरैया", "आजमगढ़", "बाराबंकी उत्तर प्रदेश", "बागपत", "बदायूं", "बहराइच उत्तर प्रदेश",
                    "बिजनौर", "बलिया उत्तर प्रदेश", "बांदा उत्तर प्रदेश", "बलरामपुर उत्तर प्रदेश", "बरेली", "बस्ती उत्तर प्रदेश", "बुलंदशहर", "चंदौली", "चित्रकूट", "देवरिया",
                    "एटा उत्तर प्रदेश", "इटावा", "फ़िरोज़ाबाद", "फर्रुखाबाद", "फ़तेहपुर", "फैजाबाद", "गौतम बुद्ध नगर", "गोंडा", "गाजीपुर", "गोरखपुर", "गाज़ियाबाद", "हापुड़",
                    "हमीरपुर", "हरदोई", "हाथरस", "झांसी", "अमरोहा", "जौनपुर", "अंबेडकर नगर", "कन्नौज", "कानपुर", "कानपुर देहात", "कासगंज", "कौशाम्बी", "कुशीनगर",
                    "ललितपुर", "लखीमपुर खीरी", "लखनऊ", "मउ", "मेरठ", "महाराजगंज", "महोबा", "मिर्जापुर", "मुरादाबाद", "मैनपुरी", "मथुरा", "मुज़फ़्फ़रनगर", "पीलीभीत",
                    "प्रतापगढ़", "रामपुर", "रायबरेली़", "सहारनपुर", "सीतापुर", "शाहजहांपुर", "शामली", "संभल", "सिद्धार्थनगर", "सोनभद्र", "भदोही", "सुलतानपुर", "श्रावस्ती", "उन्नाव",
                    "वाराणसी", "संत कबीर नगर", "जालौन"
            )),
            new ArrayList<>(Arrays.asList(
                    "अल्मोड़ा", "बागेश्वर", "चमोली", "चंपावत", "देहरादून", "हरिद्वार", "नैनीताल", "पौड़ी गढ़वाल", "पिथौरागढ़", "रुद्रप्रयाग", "टिहरी गढ़वाल", "उधम सिंह नगर", "उत्तरकाशी़"
            )),
            new ArrayList<>(Arrays.asList(
                    "लुधियाना", "अमृतसर", "जालंधर", "साहिबजादा अजीत सिंह नगर", "गुरदासपुर", "पटियाला", "फतेहगढ़ साहिब", "कपूरथला", "रूपनगर",
                    "शहीद भगत सिंह नगर", "होशियारपुर", "तरनतारन", "संगरूर", "मोगा", "फरीदकोट", "बरनाला", "बठिंडा", "फिरोजपुर", "मानसा", "मुक्तसर",
                    "पठानकोट", "फ़ज़िलका"
            )),
            new ArrayList<>(Arrays.asList(
                    "अजमेर", "अलवर", "बांसवाड़ा़", "बारन", "बाडमेर", "भरतपुऱ", "भीलवाड़ा़", "बीकानेर", "बूंदी", "चित्तौड़गढ़़", "चूरु", "दौसा", "धौलपुर",
                    "डूंगरपुर", "हनुमानगढ़", "जयपुर", "जैसलमेर", "जालोर", "झालावाड़", "झुन्झुनू़", "जोधपुऱ", "करौली", "कोटा़", "नागौऱ", "पाली़", "प्रतापगढ़",
                    "राजसमन्द", "सवाई माधोपुर", "सीकर", "सिरोही", "श्री गंगानगर", "टोंक", "उदयपुर"
            )),
            new ArrayList<>(Arrays.asList(
                    "पूर्वी सिक्किम", "उत्तर सिक्किम", "दक्षिण सिक्किम", "पश्चिम सिक्किम"
            )),
            new ArrayList<>(Arrays.asList(
                    "अरियालुर", "चेन्नई", "कोयंबटूर", "कुड्डालोर", "धर्मपुरी", "डिंडीगुल", "इरोड", "कांचीपुरम", "कन्याकुमारी", "करूर", "कृष्णागिरि", "मदुरई",
                    "नागपट्टनम", "नमक्कल", "पेरम्बलुर", "पुदुक्कोट्टई", "रामनाथपुरम", "सलेम", "शिवगंगा", "तंजावुर", "नीलगिरी", "थेनी", "तिरुवल्लुर", "तिरुवरुर",
                    "थूथुक्कुडी", "तिरुचिरापल्ली", "तिरुनेलवेली", "तिरुपुर", "तिरुवन्नामलाई", "वेल्लोर", "वेल्लोर", "विरुधुनगर"
            )),
            new ArrayList<>(Arrays.asList(
                    "धलाई", "उत्तर त्रिपुरा", "दक्षिणी त्रिपुरा", "पश्चिम त्रिपुरा", "खोवाई", "सिपाहीजाला", "गोमती", "उनाकोटी "
            )),
            new ArrayList<>(Arrays.asList(
                    "दक्षिण दिनाजपुर", "पुरुलिया", "दक्षिण 24 परगना", "उत्तर 24 परगना", "नादिया", "मुर्शिदाबाद", "पूर्ब मेदिनीपुर", "पश्चिम मेदिनीपुर",
                    "मालदा", "कोलकाता", "कूच बिहार", "जलपाईगुड़ी", "हावड़ा", "हुगली", "उत्तर दिनाजपुर", "दार्जिलिंग", "बर्धमान", "बांकुरा", "बीरभूम", "अलीपुरद्वार", "झारग्राम "
            )),
            new ArrayList<>(Arrays.asList(
                    "बालोद", "बलोदा बाजार", "बलरामपुर-रामानुजगंज", "बस्तर", "बेमेतरा", "बीजापुर", "बिलासपुर", "दन्तेवाड़ा", "धमतरी", "दुर्ग",
                    "गरियाबंद", "जांजगीर-चाम्पा", "जशपुर", "कवर्धा", "कांकेर", "कोण्डागांव", "कोरबा", "कोरिया", "महासमुन्द", "मुंगेली", "नारायणपुर",
                    "रायपुर", "रायगढ़", "राजनांदगांव", "सुकमा", "सूरजपुर", "सुरगुजा"
            )),
            new ArrayList<>(Arrays.asList(
                    "रांची", "धनबाद", "गिरिडीह", "पूर्वी सिंहभूम", "बोकारो", "पलामू", "हजारीबाग", "पश्चिमी सिंहभूम", "देवघर", "गढवा", "दुमका",
                    "गोड्डा", "साहिबगंज", "सरायकेला खरसावां", "चतरा", "गुमला", "रामगढ़", "पाकुड़", "जामताड़ा", "लातेहार", "कोडरमा", "सिमडेगा", "खूंटी", "लोहरदग्गा"
            )),
            new ArrayList<>(Arrays.asList(
                    "आदिलाबाद", "कोमराम भीम", "भद्राद्री कोठागुडम", "जयशंकर भूपपाल", "जोगुलबा गडवाल", "हैदराबाद", "जागतिअल", "जनगांव",
                    "कामारेड्डी", "करीमनगर", "खम्मम", "महबुबाबाद", "महबूबनगर", "मंचेरिअल", "मेडक", "मेडचाल मल्काजगिरि", "नलगोंडा", "नगरकुरनूल",
                    "निर्मल", "निजामाबाद", "रंगारेड्डी", "पेड्डापल्ली", "राजन्ना सिर्सिल्ला", "संगारेड्डी", "सिद्दिपेट", "सूर्यापेट", "विकाराबाद", "वनापर्थी", "वारंगल शहर",
                    "वारंगल (ग्रामीण)", "यदाद्रि भुवनगिरी", "नारायणपेट", "सम्मक्का मुलुगु"
            )),
            new ArrayList<>(Arrays.asList(
                    "दरयागंज", "सदरबाजार", "साकेत", "प्रीतविहार", "शाहदरा", "पालम, ", "राजौरीगार्डन", "कनाटपैलेस", "कंझावला"
            )),
            new ArrayList<>(Arrays.asList(
                    "दक्षिण अंडमान", " उत्तर और मध्य अंडमान", " निकोबार"
            )),
            new ArrayList<>(Arrays.asList(
                    "सिल्वासा"
            )),
            new ArrayList<>(Arrays.asList(
                    "दमन", "दीव"
            )),
            new ArrayList<>(Arrays.asList(
                    "लक्षद्वीप"
            )),
            new ArrayList<>(Arrays.asList(
                    "कराईकल", "माहे", "पांडिचेरी", "यानम"
            )),
            new ArrayList<>(Arrays.asList(
                    "लेह"
            ))
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        department = (Spinner) findViewById(R.id.spin1_type);
        type = (Spinner) findViewById(R.id.spin2_prakar);
        spec = (Spinner)findViewById(R.id.spin3_specialisation);

//        mAuth = FirebaseAuth.getInstance();
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference reff = database.getReference("hospital");


        state = findViewById(R.id.hospital_state);
        district = findViewById(R.id.hospital_dist);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(doctor_detail.this, android.R.layout.simple_spinner_item, states);
        state.setAdapter(dataAdapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stitem = parent.getItemAtPosition(position).toString();
                st = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(doctor_detail.this, android.R.layout.simple_spinner_item, districts.get(position));
                district.setAdapter(dataAdapter);
//                diitem = parent.getItemAtPosition(position).toString();
//                dt = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner spinner_1 = findViewById(R.id.spin1_type);
        Spinner spinner_2 = findViewById(R.id.spin2_prakar);
        Spinner spinner_3 = findViewById(R.id.spin3_specialisation);
        ArrayAdapter<CharSequence> adapter1 = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(doctor_detail.this, R.array.department, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(doctor_detail.this, R.array.doctor_type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(doctor_detail.this, R.array.doctor_spec, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter1);
        spinner_1.setOnItemSelectedListener( doctor_detail.this);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter2);
        spinner_2.setOnItemSelectedListener(doctor_detail.this);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_3.setAdapter(adapter3);
        spinner_3.setOnItemSelectedListener(doctor_detail.this);

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citem = parent.getItemAtPosition(position).toString();
                c = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }

        });
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sitem = parent.getItemAtPosition(position).toString();
                s = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }

        });
        spec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spitem = parent.getItemAtPosition(position).toString();
                sp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }

        });

        hos_name = findViewById(R.id.edit1);
        hos_add = findViewById(R.id.edit2);
        doc_name = findViewById(R.id.edit3);
        doc_aadhar = findViewById(R.id.edit4);
        doc_phn = findViewById(R.id.edit5);
        doc_degree = findViewById(R.id.edit6);
        doc_add = findViewById(R.id.edit7);
        doc_pin = findViewById(R.id.edit8);
        sub = (Button) findViewById(R.id.submit_doctor_detail);

//        doctor = new doctor();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hname = hos_name.getText().toString().trim();
                String hospital_type = type.getSelectedItem().toString();
                String dname = doc_name.getText().toString().trim();
                String aadhar = doc_aadhar.getText().toString().trim();
                String dphn = doc_phn.getText().toString().trim();
                String degree = doc_degree.getText().toString().trim();
                String dadd = doc_add.getText().toString().trim();
                String dpin = doc_pin.getText().toString().trim();
                String hospital_department = department.getSelectedItem().toString();
                String hadd = hos_add.getText().toString().trim();
                String hospital_spec = spec.getSelectedItem().toString();
                String hospital_state = state.getSelectedItem().toString();
                String hospital_district = district.getSelectedItem().toString();
                String hospital_pincode = doc_pin.getText().toString().trim();
                Integer pincode = Integer.parseInt(hospital_pincode);

//                Integer i = 24;
//                if (hname.isEmpty()) {
//                    hos_name.setError("Doctor's Father Name is Required");
//                    hos_name.requestFocus();
//                    return;
//                }
//                if (hadd.isEmpty()) {
//                    hos_add.setError("Doctor's Father Name is Required");
//                    hos_add.requestFocus();
//                    return;
//                }
//                if (dname.isEmpty()) {
//                    doc_name.setError("Doctor Name is Required");
//                    doc_name.requestFocus();
//                    return;
//                }
//                if (aadhar.isEmpty()) {
//                    doc_aadhar.setError("Aadhar is Required");
//                    doc_aadhar.requestFocus();
//                    return;
//                }
//                if (dphn.isEmpty()) {
//                    doc_phn.setError("Phone Number is Required");
//                    doc_phn.requestFocus();
//                    return;
//                }
//                if (degree.isEmpty()) {
//                    doc_degree.setError("Doctor Degree is Required");
//                    doc_degree.requestFocus();
//                    return;
//                }
//
//                if (dadd.isEmpty()) {
//                    doc_add.setError("Doctor's Specialisation is Required");
//                    doc_add.requestFocus();
//                    return;
//                }
//
//                if (dpin.isEmpty()) {
//                    doc_pin.setError("Doctor Type is Required");
//                    doc_pin.requestFocus();
//                    return;
//                }

                Call<ResponseBody> call= RetrofitClient.getInstance()
                        .getInterface()
                        .doctorDetails(
                                hname,
                                hospital_type,
                                dname,
                                aadhar,
                                hadd,
                                hospital_spec,
                                dadd,
                                hospital_state,
                                hospital_district,
                                pincode
                        );

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                         ResponseBody dr=response.body();
//                         Toast.makeText(hospital_reg.this,"User Created Successfully",Toast.LENGTH_LONG).show();
//                        String s = response.body().string();
                            Toast.makeText(doctor_detail.this,"User Created Successfully"+s,Toast.LENGTH_LONG).show();
                            getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Intent intent = new Intent(doctor_detail.this, hospital_login.class);
                            startActivity(intent);

                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
