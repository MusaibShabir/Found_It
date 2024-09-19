package com.example.foundit.presentation.screens.registration.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneNumberScreen(
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCountryCode by remember { mutableStateOf("+91") }
    var phoneNumber by remember { mutableStateOf("") }

    val countryCodes = listOf(
        "+1", // United States, Canada, etc.
        "+7", // Russia, Kazakhstan
        "+20", // Egypt
        "+27", // South Africa
        "+30", // Greece
        "+31", // Netherlands
        "+32", // Belgium
        "+33", // France
        "+34", // Spain
        "+36", // Hungary
        "+39", // Italy
        "+40", // Romania
        "+41", // Switzerland
        "+43", // Austria
        "+44", // United Kingdom
        "+45", // Denmark
        "+46", // Sweden
        "+47", // Norway
        "+48", // Poland
        "+49", // Germany
        "+51", // Peru
        "+52", // Mexico
        "+53", // Cuba
        "+54", // Argentina
        "+55", // Brazil
        "+56", // Chile
        "+57", // Colombia
        "+58", // Venezuela
        "+60", // Malaysia
        "+61", // Australia
        "+62", // Indonesia
        "+63", // Philippines
        "+64", // New Zealand
        "+65", // Singapore
        "+66", // Thailand
        "+81", // Japan
        "+82", // South Korea
        "+84", // Vietnam
        "+86", // China
        "+90", // Turkey
        "+91", // India
        "+92", // Pakistan
        "+93", // Afghanistan
        "+94", // Sri Lanka
        "+95", // Myanmar
        "+98", // Iran
        "+212", // Morocco
        "+213", // Algeria
        "+216", // Tunisia
        "+218", // Libya
        "+220", // Gambia
        "+221", // Senegal
        "+222", // Mauritania
        "+223", // Mali
        "+224", // Guinea
        "+225", // Ivory Coast
        "+226", // Burkina Faso
        "+227", // Niger
        "+228", // Togo
        "+229", // Benin
        "+230", // Mauritius
        "+231", // Liberia
        "+232", // Sierra Leone
        "+233", // Ghana
        "+234", // Nigeria
        "+235", // Chad
        "+236", // Central African Republic
        "+237", // Cameroon
        "+238", // Cape Verde
        "+239", // Sao Tome and Principe
        "+240", // Equatorial Guinea
        "+241", // Gabon
        "+242", // Republic of the Congo
        "+243", // Democratic Republic of the Congo
        "+244", // Angola
        "+245", // Guinea-Bissau
        "+246", // British Indian Ocean Territory
        "+248", // Seychelles
        "+249", // Sudan
        "+250", // Rwanda
        "+251", // Ethiopia
        "+252", // Somalia
        "+253", // Djibouti
        "+254", // Kenya
        "+255", // Tanzania
        "+256", // Uganda
        "+257", // Burundi
        "+258", // Mozambique
        "+260", // Zambia
        "+261", // Madagascar
        "+262", // Reunion
        "+263", // Zimbabwe
        "+264", // Namibia
        "+265", // Malawi
        "+266", // Lesotho
        "+267", // Botswana
        "+268", // Swaziland
        "+269", // Comoros
        "+290", // Saint Helena
        "+291", // Eritrea
        "+297", // Aruba
        "+298", // Faroe Islands
        "+299", // Greenland
        "+350", // Gibraltar
        "+351", // Portugal
        "+352", // Luxembourg
        "+353", // Ireland
        "+354", // Iceland
        "+355", // Albania
        "+356", // Malta
        "+357", // Cyprus
        "+358", // Finland
        "+359", // Bulgaria
        "+370", // Lithuania
        "+371", // Latvia
        "+372", // Estonia
        "+373", // Moldova
        "+374", // Armenia
        "+375", // Belarus
        "+376", // Andorra
        "+377", // Monaco
        "+378", // San Marino
        "+380", // Ukraine
        "+381", // Serbia
        "+382", // Montenegro
        "+383", // Kosovo
        "+385", // Croatia
        "+386", // Slovenia
        "+387", // Bosnia and Herzegovina
        "+389", // North Macedonia
        "+420", // Czech Republic
        "+421", // Slovakia
        "+423", // Liechtenstein
        "+500", // Falkland Islands
        "+501", // Belize
        "+502", // Guatemala
        "+503", // El Salvador
        "+504", // Honduras
        "+505", // Nicaragua
        "+506", // Costa Rica
        "+507", // Panama
        "+508", // Saint Pierre and Miquelon
        "+509", // Haiti
        "+590", // Saint Barthelemy, Saint Martin
        "+591", // Bolivia
        "+592", // Guyana
        "+593", // Ecuador
        "+594", // French Guiana
        "+595", // Paraguay
        "+596", // Martinique
        "+597", // Suriname
        "+598", // Uruguay
        "+599", // Curacao
        "+670", // East Timor
        "+672", // Antarctica, Christmas Island, Cocos Islands, Norfolk Island
        "+673", // Brunei
        "+674", // Nauru
        "+675", // Papua New Guinea
        "+676", // Tonga
        "+677", // Solomon Islands
        "+678", // Vanuatu
        "+679", // Fiji
        "+680", // Palau
        "+681", // Wallis and Futuna
        "+682", // Cook Islands
        "+683", // Niue
        "+685", // Samoa
        "+686", // Kiribati
        "+687", // New Caledonia
        "+688", // Tuvalu
        "+689", // French Polynesia
        "+690", // Tokelau
        "+691", // Micronesia
        "+692", // Marshall Islands
        "+850", // North Korea
        "+852", // Hong Kong
        "+853", // Macau
        "+855", // Cambodia
        "+856", // Laos
        "+880", // Bangladesh
        "+886", // Taiwan
        "+960", // Maldives
        "+961", // Lebanon
        "+962", // Jordan
        "+963", // Syria
        "+964", // Iraq
        "+965", // Kuwait
        "+966", // Saudi Arabia
        "+967", // Yemen
        "+968", // Oman
        "+970", // Palestine
        "+971", // United Arab Emirates
        "+972", // Israel
        "+973", // Bahrain
        "+974", // Qatar
        "+975", // Bhutan
        "+976", // Mongolia
        "+977", // Nepal
        "+992", // Tajikistan
        "+993", // Turkmenistan
        "+994", // Azerbaijan
        "+995", // Georgia
        "+996", // Kyrgyzstan
        "+998" // Uzbekistan
    )


    Scaffold {
        Column(
            modifier = modifier
                .padding(start = 20.dp, end = 20.dp, top = 250.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

            ){

            }
            Text(
                text = "Enter your phone number",
                fontSize = 20.sp,
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Exposed Dropdown Menu for country codes
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded } // Toggles dropdown
                ) {
                    //Country Code
                    OutlinedTextField(
                        value = selectedCountryCode,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        singleLine = true,
                        modifier = modifier.width(110.dp)
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable , enabled = true)
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {

                        countryCodes.forEach { cc ->
                            DropdownMenuItem(
                                text = { Text(text = cc) },
                                onClick = {
                                    selectedCountryCode = cc
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Outlined TextField for phone number input
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        // Allow only numeric input and restrict to 10 digits
                        if (it.all { char -> char.isDigit() } && it.length <= 10) {
                            phoneNumber = it
                        }
                    },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                )
            }


            Button(
                onClick = {
                    // Handle phone number verification logic
                },
                modifier = modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                Text("Verify")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PhoneNumberScreenPreview() {
    PhoneNumberScreen(modifier = Modifier)
}
