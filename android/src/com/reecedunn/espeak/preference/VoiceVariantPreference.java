/*
 * Copyright (C) 2022 Beka Gozalishvili
 * Copyright (C) 2013 Reece H. Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reecedunn.espeak.preference;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.reecedunn.espeak.R;
import com.reecedunn.espeak.ResourceIdListAdapter;
import com.reecedunn.espeak.VoiceSettings;
import com.reecedunn.espeak.VoiceVariant;

public class VoiceVariantPreference extends DialogPreference {
    private Spinner mCategory;
    private Spinner mVariant;

    private int mCategoryIndex = 0;
    private int mVariantIndex = 0;

    static class ViewHolder
    {
        public TextView text;
    }

    private class VariantData {
        private final int name;
        private final Object arg;
        private final VoiceVariant variant;

        protected VariantData(int name, String variant) {
            this(name, null, variant);
        }

        protected VariantData(int name, Object arg, String variant) {
            this.name = name;
            this.arg = arg;
            this.variant = VoiceVariant.parseVoiceVariant(variant);
        }

        public String getDisplayName(Context context) {
            String text = context.getText(name).toString();
            if (arg == null) {
                return text;
            }
            return String.format(text, arg);
        }

        public VoiceVariant getVariant() {
            return variant;
        }
    }

    public class VariantDataListAdapter extends ArrayAdapter<VariantData>
    {
        private final LayoutInflater mInflater;

        public VariantDataListAdapter(Activity context, VariantData[] resources)
        {
            super(context, android.R.layout.simple_list_item_1, resources);
            mInflater = context.getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;
            if (convertView == null)
            {
                convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView)convertView.findViewById(android.R.id.text1);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.text.setText(getItem(position).getDisplayName(getContext()));
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            return getView(position, convertView, parent);
        }
    }

    private Integer[] categories = {
        R.string.variant_nvda,
    };

    private VariantData[][] variants = {
        {
new VariantData(R.string.variant_none, "none"),
new VariantData(R.string.variant_Adam2, "Adam2"),
new VariantData(R.string.variant_Alex, "Alex"),
new VariantData(R.string.variant_Alex2, "Alex2"),
new VariantData(R.string.variant_Alex3, "Alex3"),
new VariantData(R.string.variant_Alexanderia, "Alexanderia"),
new VariantData(R.string.variant_Alian, "Alian"),
new VariantData(R.string.variant_Alian_3, "Alian_3"),
new VariantData(R.string.variant_Alicia, "Alicia"),
new VariantData(R.string.variant_Alicia2, "Alicia2"),
new VariantData(R.string.variant_Alicia3, "Alicia3"),
new VariantData(R.string.variant_Alien_2, "Alien_2"),
new VariantData(R.string.variant_Andrea, "Andrea"),
new VariantData(R.string.variant_Andrea2, "Andrea2"),
new VariantData(R.string.variant_Andrea3, "Andrea3"),
new VariantData(R.string.variant_Andres, "Andres"),
new VariantData(R.string.variant_Andy, "Andy"),
new VariantData(R.string.variant_Andy2, "Andy2"),
new VariantData(R.string.variant_Andy3, "Andy3"),
new VariantData(R.string.variant_Annie, "Annie"),
new VariantData(R.string.variant_Annie2, "Annie2"),
new VariantData(R.string.variant_Antonio2, "Antonio2"),
new VariantData(R.string.variant_AnxiousAndy, "anxiousAndy"),
new VariantData(R.string.variant_AnxiousAndy2, "AnxiousAndy2"),
new VariantData(R.string.variant_Auntie2, "Auntie2"),
new VariantData(R.string.variant_Awkward, "Awkward"),
new VariantData(R.string.variant_BNS, "BNS"),
new VariantData(R.string.variant_Baby, "Baby"),
new VariantData(R.string.variant_Belinda2, "Belinda2"),
new VariantData(R.string.variant_Benjamin2, "Benjamin2"),
new VariantData(R.string.variant_Benjamin3, "Benjamin3"),
new VariantData(R.string.variant_Betty, "Betty"),
new VariantData(R.string.variant_Betty2, "Betty2"),
new VariantData(R.string.variant_Betty3, "Betty3"),
new VariantData(R.string.variant_Betty4, "Betty4"),
new VariantData(R.string.variant_Bill, "Bill"),
new VariantData(R.string.variant_Bill_2, "Bill_2"),
new VariantData(R.string.variant_Bob, "Bob"),
new VariantData(R.string.variant_Bobby, "Bobby"),
new VariantData(R.string.variant_Bobby2, "Bobby2"),
new VariantData(R.string.variant_Bobby3, "Bobby3"),
new VariantData(R.string.variant_Bob_2, "Bob_2"),
new VariantData(R.string.variant_BrailleNSpeak_2, "BrailleNSpeak_2"),
new VariantData(R.string.variant_BrailleNSpeak_3, "BrailleNSpeak_3"),
new VariantData(R.string.variant_Bug, "Bug"),
new VariantData(R.string.variant_Caleb2, "Caleb2"),
new VariantData(R.string.variant_CaveMan, "CaveMan"),
new VariantData(R.string.variant_Caveman, "Caveman"),
new VariantData(R.string.variant_Caveman2, "Caveman2"),
new VariantData(R.string.variant_Chad, "Chad"),
new VariantData(R.string.variant_Chad2, "Chad2"),
new VariantData(R.string.variant_Chad3, "Chad3"),
new VariantData(R.string.variant_Chad_4, "Chad_4"),
new VariantData(R.string.variant_Chipmunk, "Chipmunk"),
new VariantData(R.string.variant_Colossus, "Colossus"),
new VariantData(R.string.variant_Croak2, "Croak2"),
new VariantData(R.string.variant_CrushedBugAboutToDie, "CrushedBugAboutToDie"),
new VariantData(R.string.variant_Daniel, "Daniel"),
new VariantData(R.string.variant_Daniel2, "Daniel2"),
new VariantData(R.string.variant_Dave, "Dave"),
new VariantData(R.string.variant_Dave2, "Dave2"),
new VariantData(R.string.variant_Dave3, "Dave3"),
new VariantData(R.string.variant_Dave4, "Dave4"),
new VariantData(R.string.variant_Dave5, "Dave5"),
new VariantData(R.string.variant_Dave6, "Dave6"),
new VariantData(R.string.variant_David2, "David2"),
new VariantData(R.string.variant_DeepMale, "DeepMale"),
new VariantData(R.string.variant_Delta, "Delta"),
new VariantData(R.string.variant_Demonic, "Demonic"),
new VariantData(R.string.variant_Demonic2, "Demonic2"),
new VariantData(R.string.variant_Denis, "Denis"),
new VariantData(R.string.variant_Dennis, "Dennis"),
new VariantData(R.string.variant_Dennis2, "Dennis2"),
new VariantData(R.string.variant_Dennis3, "Dennis3"),
new VariantData(R.string.variant_Dennis4, "Dennis4"),
new VariantData(R.string.variant_Dennis_5, "Dennis_5"),
new VariantData(R.string.variant_Denture, "Denture"),
new VariantData(R.string.variant_Denture_2, "Denture_2"),
new VariantData(R.string.variant_Denture_3, "Denture_3"),
new VariantData(R.string.variant_Diogo, "Diogo"),
new VariantData(R.string.variant_Diogo2, "Diogo2"),
new VariantData(R.string.variant_DoctorDennis, "DoctorDennis"),
new VariantData(R.string.variant_DoctorDennis2, "DoctorDennis2"),
new VariantData(R.string.variant_DoctorDennis3, "DoctorDennis3"),
new VariantData(R.string.variant_DoctorDennis4, "DoctorDennis4"),
new VariantData(R.string.variant_Ed2, "Ed2"),
new VariantData(R.string.variant_Eddy, "Eddy"),
new VariantData(R.string.variant_Edith, "Edith"),
new VariantData(R.string.variant_Edward3, "Edward3"),
new VariantData(R.string.variant_Elif, "Elif"),
new VariantData(R.string.variant_Elif2, "Elif2"),
new VariantData(R.string.variant_Evil, "Evil"),
new VariantData(R.string.variant_Fast_Test2, "Fast_Test2"),
new VariantData(R.string.variant_Fatih, "Fatih"),
new VariantData(R.string.variant_Fatih2, "Fatih2"),
new VariantData(R.string.variant_Female6, "Female6"),
new VariantData(R.string.variant_Female7, "Female7"),
new VariantData(R.string.variant_Female8, "Female8"),
new VariantData(R.string.variant_Female9, "Female9"),
new VariantData(R.string.variant_Female_caleb, "Female_caleb"),
new VariantData(R.string.variant_Franco, "Franco"),
new VariantData(R.string.variant_Frank, "Frank"),
new VariantData(R.string.variant_Frank2, "Frank2"),
new VariantData(R.string.variant_Frog_2, "Frog_2"),
new VariantData(R.string.variant_Gene, "Gene"),
new VariantData(R.string.variant_Gene2, "Gene2"),
new VariantData(R.string.variant_Gene3, "Gene3"),
new VariantData(R.string.variant_Gene4, "Gene4"),
new VariantData(R.string.variant_Gene5, "Gene5"),
new VariantData(R.string.variant_George, "George"),
new VariantData(R.string.variant_George_2, "George_2"),
new VariantData(R.string.variant_GiantMonster, "GiantMonster"),
new VariantData(R.string.variant_GiantRobot, "GiantRobot"),
new VariantData(R.string.variant_GivingInstructions, "GivingInstructions"),
new VariantData(R.string.variant_GivingInstructions2, "GivingInstructions2"),
new VariantData(R.string.variant_Glen, "Glen"),
new VariantData(R.string.variant_Grim_Reaper, "Grim_Reaper"),
new VariantData(R.string.variant_Gustave2, "Gustave2"),
new VariantData(R.string.variant_HalfLifeAnnouncementSystem_2, "HalfLifeAnnouncementSystem_2"),
new VariantData(R.string.variant_Harry, "Harry"),
new VariantData(R.string.variant_Harry2, "Harry2"),
new VariantData(R.string.variant_HarryRobot, "HarryRobot"),
new VariantData(R.string.variant_Henrique, "Henrique"),
new VariantData(R.string.variant_Henrique2, "Henrique2"),
new VariantData(R.string.variant_Hugo, "Hugo"),
new VariantData(R.string.variant_Hugo2, "Hugo2"),
new VariantData(R.string.variant_Ian, "Ian"),
new VariantData(R.string.variant_Ian2, "Ian2"),
new VariantData(R.string.variant_Iven5, "Iven5"),
new VariantData(R.string.variant_Jackie0, "Jackie0"),
new VariantData(R.string.variant_Jackie1, "Jackie1"),
new VariantData(R.string.variant_Jacky, "Jacky"),
new VariantData(R.string.variant_Jacky2, "Jacky2"),
new VariantData(R.string.variant_Jason, "Jason"),
new VariantData(R.string.variant_Jason2, "Jason2"),
new VariantData(R.string.variant_Jason_3, "Jason_3"),
new VariantData(R.string.variant_John2, "John2"),
new VariantData(R.string.variant_Jose, "Jose"),
new VariantData(R.string.variant_Josh, "Josh"),
new VariantData(R.string.variant_Josh2, "Josh2"),
new VariantData(R.string.variant_Julie, "Julie"),
new VariantData(R.string.variant_Julie2, "Julie2"),
new VariantData(R.string.variant_Kevin, "Kevin"),
new VariantData(R.string.variant_Kit, "Kit"),
new VariantData(R.string.variant_Kit2, "Kit2"),
new VariantData(R.string.variant_Kit3, "Kit3"),
new VariantData(R.string.variant_Kit4, "Kit4"),
new VariantData(R.string.variant_Kit5, "Kit5"),
new VariantData(R.string.variant_Larry, "Larry"),
new VariantData(R.string.variant_Larry2, "Larry2"),
new VariantData(R.string.variant_Leann, "Leann"),
new VariantData(R.string.variant_Lee, "Lee"),
new VariantData(R.string.variant_Lee2, "Lee2"),
new VariantData(R.string.variant_Male9, "Male9"),
new VariantData(R.string.variant_Marcelo2, "Marcelo2"),
new VariantData(R.string.variant_Marcelo3, "Marcelo3"),
new VariantData(R.string.variant_Marco, "Marco"),
new VariantData(R.string.variant_Marco2, "Marco2"),
new VariantData(R.string.variant_Mario, "Mario"),
new VariantData(R.string.variant_Mario2, "Mario2"),
new VariantData(R.string.variant_Max2, "Max2"),
new VariantData(R.string.variant_Max3, "Max3"),
new VariantData(R.string.variant_Max4, "Max4"),
new VariantData(R.string.variant_Max5, "Max5"),
new VariantData(R.string.variant_Michael, "Michael"),
new VariantData(R.string.variant_Michael2, "Michael2"),
new VariantData(R.string.variant_Michel2, "Michel2"),
new VariantData(R.string.variant_Miguel2, "Miguel2"),
new VariantData(R.string.variant_Mike, "Mike"),
new VariantData(R.string.variant_Mike2, "Mike2"),
new VariantData(R.string.variant_Mike3, "Mike3"),
new VariantData(R.string.variant_MisterSilly, "MisterSilly"),
new VariantData(R.string.variant_Mr_serious, "Mr_Serious"),
new VariantData(R.string.variant_Mr_Silly_2, "Mr_Silly_2"),
new VariantData(R.string.variant_Muhammed, "Muhammed"),
new VariantData(R.string.variant_Muhammed2, "Muhammed2"),
new VariantData(R.string.variant_Nguyen, "Nguyen"),
new VariantData(R.string.variant_Nguyen2, "Nguyen2"),
new VariantData(R.string.variant_Osman, "Osman"),
new VariantData(R.string.variant_Osman2, "Osman2"),
new VariantData(R.string.variant_Pablo2, "Pablo2"),
new VariantData(R.string.variant_Paul2, "Paul2"),
new VariantData(R.string.variant_Paul3, "Paul3"),
new VariantData(R.string.variant_PotatoTruck, "PotatoTruck"),
new VariantData(R.string.variant_PureLisp, "PureLisp"),
new VariantData(R.string.variant_Quincy2, "Quincy2"),
new VariantData(R.string.variant_Read, "Read"),
new VariantData(R.string.variant_Read2, "Read2"),
new VariantData(R.string.variant_Read3, "Read3"),
new VariantData(R.string.variant_Recep, "Recep"),
new VariantData(R.string.variant_Recep2, "Recep2"),
new VariantData(R.string.variant_Reed, "Reed"),
new VariantData(R.string.variant_Reed2, "Reed2"),
new VariantData(R.string.variant_RicishayMax, "RicishayMax"),
new VariantData(R.string.variant_RicishayMax2, "RicishayMax2"),
new VariantData(R.string.variant_RicishayMax3, "RicishayMax3"),
new VariantData(R.string.variant_RicochetMax0, "RicochetMax0"),
new VariantData(R.string.variant_RicochetMax1, "RicochetMax1"),
new VariantData(R.string.variant_RicochetMax2, "RicochetMax2"),
new VariantData(R.string.variant_RicochetMax3, "RicochetMax3"),
new VariantData(R.string.variant_RicochetMax4, "RicochetMax4"),
new VariantData(R.string.variant_Rob2, "Rob2"),
new VariantData(R.string.variant_Robert2, "Robert2"),
new VariantData(R.string.variant_Robert3, "Robert3"),
new VariantData(R.string.variant_Rodger, "Rodger"),
new VariantData(R.string.variant_Rodger2, "Rodger2"),
new VariantData(R.string.variant_Rodger_2, "Rodger_2"),
new VariantData(R.string.variant_Sandro2, "Sandro2"),
new VariantData(R.string.variant_Sandy, "Sandy"),
new VariantData(R.string.variant_Sandy2, "Sandy2"),
new VariantData(R.string.variant_Sarah, "Sarah"),
new VariantData(R.string.variant_SeaBondDentureAdhesive, "SeaBondDentureAdhesive"),
new VariantData(R.string.variant_Shelby2, "Shelby2"),
new VariantData(R.string.variant_Shelley, "Shelley"),
new VariantData(R.string.variant_SinusPressure, "SinusPressure"),
new VariantData(R.string.variant_Sonya, "Sonya"),
new VariantData(R.string.variant_Sonya2, "Sonya2"),
new VariantData(R.string.variant_Steph4, "Steph4"),
new VariantData(R.string.variant_Storm, "Storm"),
new VariantData(R.string.variant_Storm2, "Storm2"),
new VariantData(R.string.variant_Susan, "Susan"),
new VariantData(R.string.variant_Susan2, "Susan2"),
new VariantData(R.string.variant_Susan3, "Susan3"),
new VariantData(R.string.variant_Susan4, "Susan4"),
new VariantData(R.string.variant_Susan5, "Susan5"),
new VariantData(R.string.variant_Susan6, "Susan6"),
new VariantData(R.string.variant_Tom, "Tom"),
new VariantData(R.string.variant_Tom2, "Tom2"),
new VariantData(R.string.variant_Tom3, "Tom3"),
new VariantData(R.string.variant_Tom_4, "Tom_4"),
new VariantData(R.string.variant_Tortured, "Tortured"),
new VariantData(R.string.variant_Tweaky, "Tweaky"),
new VariantData(R.string.variant_Tweaky2, "Tweaky2"),
new VariantData(R.string.variant_UniRobot, "UniversalRobot"),
new VariantData(R.string.variant_Ursula, "Ursula"),
new VariantData(R.string.variant_Ursula2, "Ursula2"),
new VariantData(R.string.variant_Ursula3, "Ursula3"),
new VariantData(R.string.variant_VoiceoverArtist, "VoiceoverArtist"),
new VariantData(R.string.variant_Wave, "Wave"),
new VariantData(R.string.variant_Wave2, "Wave2"),
new VariantData(R.string.variant_Wave3, "Wave3"),
new VariantData(R.string.variant_Whisper2, "Whisper2"),
new VariantData(R.string.variant_Whisper3, "Whisper3"),
new VariantData(R.string.variant_Wierd_Variant, "Wierd_Variant"),
new VariantData(R.string.variant_Winston, "Winston"),
new VariantData(R.string.variant_Yelling, "Yelling"),
new VariantData(R.string.variant_Yelling2, "Yelling2"),
new VariantData(R.string.variant_YoungPaul3, "YoungPaul3"),
new VariantData(R.string.variant_ZBondDenture, "ZBondDenture"),
new VariantData(R.string.variant_Zachary, "Zachary"),
new VariantData(R.string.variant_Zira, "Zira"),
new VariantData(R.string.variant_adam, "Adam"),
new VariantData(R.string.variant_anika, "anika"),
new VariantData(R.string.variant_anikaRobot, "anikaRobot"),
new VariantData(R.string.variant_announcer, "Half-LifeAnnouncementSystem"),
new VariantData(R.string.variant_antonio, "Antonio"),
new VariantData(R.string.variant_aunty, "Auntie"),
new VariantData(R.string.variant_belinda, "Belinda"),
new VariantData(R.string.variant_benjamin, "Benjamin"),
new VariantData(R.string.variant_boris, "Boris"),
new VariantData(R.string.variant_caleb, "Caleb"),
new VariantData(R.string.variant_croak, "croak"),
new VariantData(R.string.variant_david, "David"),
new VariantData(R.string.variant_ed, "Ed"),
new VariantData(R.string.variant_edward, "Edward"),
new VariantData(R.string.variant_edward2, "Edward2"),
new VariantData(R.string.variant_f1, "female1"),
new VariantData(R.string.variant_f2, "female2"),
new VariantData(R.string.variant_f3, "female3"),
new VariantData(R.string.variant_f4, "female4"),
new VariantData(R.string.variant_f5, "female5"),
new VariantData(R.string.variant_fast, "fast_test"),
new VariantData(R.string.variant_grandma, "grandma"),
new VariantData(R.string.variant_grandpa, "grandpa"),
new VariantData(R.string.variant_gustave, "Gustave"),
new VariantData(R.string.variant_iven, "Iven"),
new VariantData(R.string.variant_iven2, "Iven2"),
new VariantData(R.string.variant_iven3, "Iven3"),
new VariantData(R.string.variant_iven4, "Iven4"),
new VariantData(R.string.variant_john, "John"),
new VariantData(R.string.variant_kaukovalta, "Kaukovalta"),
new VariantData(R.string.variant_klatt, "klatt"),
new VariantData(R.string.variant_klatt2, "klatt2"),
new VariantData(R.string.variant_klatt3, "klatt3"),
new VariantData(R.string.variant_klatt4, "klatt4"),
new VariantData(R.string.variant_klatt5, "klatt5"),
new VariantData(R.string.variant_klatt6, "klatt6"),
new VariantData(R.string.variant_linda, "Linda"),
new VariantData(R.string.variant_m1, "male1"),
new VariantData(R.string.variant_m2, "male2"),
new VariantData(R.string.variant_m3, "male3"),
new VariantData(R.string.variant_m4, "male4"),
new VariantData(R.string.variant_m5, "male5"),
new VariantData(R.string.variant_m6, "male6"),
new VariantData(R.string.variant_m7, "male7"),
new VariantData(R.string.variant_m8, "male8"),
new VariantData(R.string.variant_marcelo, "Marcelo"),
new VariantData(R.string.variant_max, "Max"),
new VariantData(R.string.variant_michel, "Michel"),
new VariantData(R.string.variant_miguel, "Miguel"),
new VariantData(R.string.variant_norbert, "norbert"),
new VariantData(R.string.variant_pablo, "Pablo"),
new VariantData(R.string.variant_paul, "Paul"),
new VariantData(R.string.variant_pedro, "Pedro"),
new VariantData(R.string.variant_quincy, "Quincy"),
new VariantData(R.string.variant_rob, "Rob"),
new VariantData(R.string.variant_robert, "Robert"),
new VariantData(R.string.variant_robosoft, "Robosoft"),
new VariantData(R.string.variant_robosoft2, "Robosoft2"),
new VariantData(R.string.variant_robosoft3, "Robosoft3"),
new VariantData(R.string.variant_robosoft4, "Robosoft4"),
new VariantData(R.string.variant_robosoft5, "Robosoft5"),
new VariantData(R.string.variant_robosoft6, "Robosoft6"),
new VariantData(R.string.variant_robosoft7, "Robosoft7"),
new VariantData(R.string.variant_robosoft8, "Robosoft8"),
new VariantData(R.string.variant_sandro, "sandro"),
new VariantData(R.string.variant_shelby, "shelby"),
new VariantData(R.string.variant_steph, "Steph"),
new VariantData(R.string.variant_steph2, "Steph2"),
new VariantData(R.string.variant_steph3, "Steph3"),
new VariantData(R.string.variant_travis, "travis"),
new VariantData(R.string.variant_victor, "victor"),
new VariantData(R.string.variant_whisper, "whisper"),
new VariantData(R.string.variant_whisperf, "female_whisper"),
new VariantData(R.string.variant_zac, "Zac"),
    },
    };

    public VoiceVariantPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDialogLayoutResource(R.layout.voice_variant_preference);
        setLayoutResource(R.layout.information_view);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
    }

    public VoiceVariantPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceVariantPreference(Context context) {
        this(context, null);
    }

    public void setVoiceVariant(VoiceVariant variant) {
        for (int i = 0; i < variants.length; ++i) {
            VariantData[] items = variants[i];
            for (int j = 0; j < items.length; ++j) {
                if (items[j].getVariant().equals(variant)) {
                    mCategoryIndex = i;
                    mVariantIndex  = j;
                    onDataChanged();
                    return;
                }
            }
        }
        onDataChanged();
    }

    @Override
    protected View onCreateDialogView() {
        View root = super.onCreateDialogView();
        mCategory = (Spinner)root.findViewById(R.id.category);
        mVariant = (Spinner)root.findViewById(R.id.variant);
        return root;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        // Cache the indices so they don't get overwritten by the OnItemSelectedListener handlers.
        final int category = mCategoryIndex;
        final int variant  = mVariantIndex;

        mCategory.setAdapter(new ResourceIdListAdapter((Activity)getContext(), categories));
        mCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean mInitializing = true;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mVariant.setAdapter(new VariantDataListAdapter((Activity) getContext(), variants[position]));
                if (mInitializing) {
                    mVariant.setSelection(variant);
                    mInitializing = false;
                }
                mCategoryIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mVariant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mVariantIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mCategory.setSelection(category);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                onDataChanged();
                if (shouldCommit()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    {
                        PreferenceManager preferenceManager = getPreferenceManager();
                        preferenceManager.setStorageDeviceProtected ();
                    }
                    SharedPreferences.Editor editor = getEditor();
                    if (editor != null) {
                        VoiceVariant variant = variants[mCategoryIndex][mVariantIndex].getVariant();
                        editor.putString(VoiceSettings.PREF_VARIANT, variant.toString());
                        editor.commit();
                    }
                }
                break;
        }
        super.onClick(dialog, which);
    }

    private void onDataChanged() {
        Context context = getContext();
        CharSequence category = context.getText(categories[mCategoryIndex]);
        CharSequence variant  = variants[mCategoryIndex][mVariantIndex].getDisplayName(context);
        callChangeListener(String.format("%s (%s)", category, variant));
    }
}
