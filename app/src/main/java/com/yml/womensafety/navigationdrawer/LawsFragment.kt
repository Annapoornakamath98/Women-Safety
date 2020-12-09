package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.Laws
import com.yml.womensafety.LawsAdapter
import com.yml.womensafety.R

class LawsFragment : Fragment(R.layout.fragment_laws) {

    private val lawList = ArrayList<Laws>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        val adapter = LawsAdapter(lawList)
        displayLawsRelatedToWomen()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }
    }

    private fun displayLawsRelatedToWomen() {
        lawList.apply {
            add(
                Laws(
                    "The Prohibition of Child Marriage Act, 2006\n",
                    "According to the International Research Centre for Women, " +
                            "almost 47 percent of girls are married before the age of 18. " +
                            "Currently, India ranks 13 in the world when it comes to child marriages. " +
                            "Since child marriage has been steeped into the Indian culture and tradition since centuries," +
                            " it has been tough eliminating it. The Prohibition of Child Marriage Act was made effective in 2007." +
                            " This act defines child marriage as a marriage where the groom or the bride are underage," +
                            " that is, the bride is under 18 years of age or the boy is younger than 21 years. " +
                            "Parents trying to marry underage girls are subject to action under this law. " +
                            "Since the law makes these marriages illegal, it acts as a major deterrent."
                )
            )

            add(
                Laws(
                    "Special Marriage Act, 1954\n",
                    "The objectives of this act is to provide – a special form of marriage in certain cases, " +
                            "provide for registration of certain marriages and, to provide for divorce. " +
                            "In a country like India and with the diverse religions and cast, " +
                            "when people from different faiths and caste chose to get married they do it under the Special Marriage Act\n" +
                            "\nIt is not applicable to the state of Jammu and Kashmir and also extends to intending spouses who are Indian nationals and living abroad."
                )
            )

            add(
                Laws(
                    "Dowry Prohibition Act, 1961\n",
                    "According to this act, taking or giving of dowry at the time of the marriage to the bride or the bridegroom" +
                            " and their family is to be penalised. Dowry system, giving and taking of dowry, is a norm in India. " +
                            "Dowry is often asked of the bride and her family by the groom and his family. " +
                            "The system has taken strong roots because women after marriage move in with their spouse and in-laws. " +
                            "Also, over the centuries, the lack for economic independence of women and the taboo towards divorce has resulted in bride burning. " +
                            "When demands for dowry even after marriage are not met by the girl’s families, " +
                            "many women are tortured, beaten and even burnt. It is one of the major challenges that our society is grappling with." +
                            " Women openly complaining about it has helped to spread the word and encourage other women to take a stand."
                )
            )

            add(
                Laws(
                    "Indian Divorce Act, 1969\n",
                    "The Indian Divorce Act allows the dissolution of marriage, mutual consent, nullity of marriage, " +
                            "judicial separation and restitution of conjugal rights. Family Courts are established to file, hear, and dispose of such cases."
                )
            )

            add(
                Laws(
                    "Maternity Benefit Act,1861\n",
                    "This act regulates the employment of women and maternity benefits mandated by law. " +
                            "It states that a woman employee who has worked in an organisation for a period of at least 80 days " +
                            "during the 12 months preceding the date of her expected delivery is entitled to receive maternity benefits, " +
                            "which includes maternity leave, nursing breaks, medical allowance, etc."
                )
            )

            add(
                Laws(
                    "Medical Termination of Pregnancy Act,1971\n",
                    "The Act came into effect into 1972, was amended in 1975 and 2002. " +
                            "The aim of the Act is to reduce the occurrence of illegal abortion and consequent maternal mortality and morbidity." +
                            " It clearly states the conditions under which a pregnancy can be ended or aborted and specifies the persons qualified to conduct the same."
                )
            )

            add(
                Laws(
                    "Sexual Harassment of Women at Workplace (Prevention, Prohibition and Redressal) Act, 2013\n",
                    "To ensure women’s safety at workplace, this Act seeks to protect them from sexual harassment at their place of work. " +
                            "Thirty-six percent of Indian companies and 25 percent among MNC’s are not complaint with the Sexual Harassment Act " +
                            "according to a FICCI-EY November 2015 report. Sexual harassment at workplace also includes – the use of language with sexual overtones," +
                            " invasion of private space with a male colleague hovering too close for comfort and subtle touches."
                )
            )

            add(
                Laws(
                    "Indecent Representation of Women(Prevention) Act,1986\n",
                    "This Act prohibits indecent representation of women through advertisement or in publications, writings, paintings, figures or in any other manner."
                )
            )

            add(
                Laws(
                    "National Commission for Women Act, 1990\n",
                    "The National Commission for Women (NCW) is a statutory body of the Government of India, established in January 1992." +
                            " Lalitha Kumaramangalam was appointed its Chairperson in 2014. The NCW represents the rights of women in India and provides" +
                            " a voice for their issues and concerns. The National Commission for Women Act aims to improve the status of women and worked for " +
                            "their economic empowerment."
                )
            )

            add(
                Laws(
                    "Equal Remuneration Act, 1976 \n",
                    "This Act prevents discrimination in terms of remuneration. " +
                            "It provides for payment of equal recompense to men and women workers."
                )
            )
        }
    }
}