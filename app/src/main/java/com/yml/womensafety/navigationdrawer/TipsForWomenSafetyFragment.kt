package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.TipsForWomenSafetyAdapter
import com.yml.womensafety.R

class TipsForWomenSafetyFragment : Fragment(R.layout.fragment_tips_for_women_safety) {

    private val tipsTextList = ArrayList<SafetyTips>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        displaySafetyTips()

        val adapter = TipsForWomenSafetyAdapter(tipsTextList)
        recyclerView.adapter = adapter
    }

    private fun displaySafetyTips() {
        tipsTextList.add(SafetyTips("If walking by yourself, get off your cell phone. Chatting can distract you, " +
                "it's better to be aware of your surroundings."))
        tipsTextList.add(SafetyTips("If walking by yourself, hold your cell phone and be ready to make an emergency call."))
        tipsTextList.add(SafetyTips("Look underneath your car before approaching it in a parking garage or lot at night."))
        tipsTextList.add(SafetyTips("If you return to your car and see that a van is parked right next to the driver's side, " +
                "enter through the passenger side. Predators often use vans and will disguise it as a family car."))
        tipsTextList.add(SafetyTips("You return to your car and it has a flat tire. Back away. " +
                "Return to wherever you came from (restaurant, store, etc) and call for help." +
                " Once assistance arrives, approach your car. " +
                "If someone comes up to you (even if they are a woman) and wants to offer help, " +
                "politely say, \"No thank you.\" If a man, he could be a predator." +
                " If a woman, she could be the lurer."))
        tipsTextList.add(SafetyTips("When the pharmacist asks you to confirm your home address, whisper it to them." +
                " Broadcasting your home address among strangers on the line could compromise your safety."))
        tipsTextList.add(SafetyTips("If you're checking in to a hotel room. " +
                "If the person at the front desk says your room number out loud, " +
                "ask them to give you a new room and write the number on a piece of paper. " +
                "Or when you check-in, ask upfront that they not say your room number out loud."))
        tipsTextList.add(SafetyTips("When traveling, do not walk with your map in your hand. " +
                "It is a dead giveaway that you are a tourist. Therefore, you are an easy target."))
        tipsTextList.add(SafetyTips("If you call for room service, and you get a knock on your door, " +
                "do not immediately open. Ask: \"Who is it?\" Make the person on the other side of the door" +
                " tell you who they are before you open it."))
        tipsTextList.add(SafetyTips("When asking for directions and someone offers to show you the way by having you follow them, do not go. Just ask for them to point you in the right direction." +
                " Often, predators just want to get you to a place less crowded where your screams can't be heard."))
        tipsTextList.add(SafetyTips("If someone tries to grab you, twist your arm up and down and yell, " +
                "\"Stop!\" Do anything you can to draw attention to yourself."))
        tipsTextList.add(SafetyTips("Always pour your own drink at a party and bring it with you everywhere...even to the bathroom. " +
                "This will make it a lot harder for someone to drug you via you drink."))
        tipsTextList.add(SafetyTips("If someone is chasing after you, run away in a zig zag pattern." +
                " This will exhaust your attacker."))
        tipsTextList.add(SafetyTips("Don't check-in on Facebook when you arrive somewhere. " +
                "Instead, check in as you leave. This way no one will be able to digitally stalk you " +
                "and know your every move or when you're not home. Along the same lines," +
                " avoid tweeting or Facebooking from vacation, especially if your account is public, " +
                "as it's a way of letting the world know that your home is unoccupied."))
    }

}