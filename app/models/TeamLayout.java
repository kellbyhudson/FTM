package models;

public class TeamLayout
{
    private Quarterback quarterback;
    private Quarterback quarterback2;
    private Quarterback quarterback3;

    private RunningBack runningBack;
    private RunningBack runningBack2;
    private RunningBack runningBack3;

    private WideReceiver1 wideReceiver1;
    private WideReceiver1 wideReceiver2;
    private WideReceiver1 wideReceiver3;
    private WideReceiver1 wideReceiver4;
    private WideReceiver1 wideReceiver5;
    private WideReceiver1 wideReceiver6;

    private TightEnd tightEnd;
    private TightEnd tightEnd2;

    private Tackle1 tackle1;
    private Tackle1 tackle2;
    private Tackle1 tackle3;
    private Tackle1 tackle4;

    private Guard1 guard1;
    private Guard1 guard2;
    private Guard1 guard3;
    private Guard1 guard4;

    private Center center;
    private Center center2;

    private DefensiveTackle1 defensiveTackle1;
    private DefensiveTackle1 defensiveTackle2;
    private DefensiveTackle1 defensiveTackle3;
    private DefensiveTackle1 defensiveTackle4;
    private DefensiveTackle1 defensiveTackle5;

    private DefensiveEnd1 defensiveEnd1;
    private DefensiveEnd1 defensiveEnd2;
    private DefensiveEnd1 defensiveEnd3;
    private DefensiveEnd1 defensiveEnd4;
    private DefensiveEnd1 defensiveEnd5;

    private OutsideLinebacker1 outsideLinebacker1;
    private OutsideLinebacker1 outsideLinebacker2;
    private OutsideLinebacker1 outsideLinebacker3;
    private OutsideLinebacker1 outsideLinebacker4;

    private InsideLinebacker insideLinebacker;
    private InsideLinebacker insideLinebacker2;
    private InsideLinebacker insideLinebacker3;

    private Safety1 safety1;
    private Safety1 safety2;
    private Safety1 safety3;
    private Safety1 safety4;

    private Cornerback1 cornerback1;
    private Cornerback1 cornerback2;
    private Cornerback1 cornerback3;
    private Cornerback1 cornerback4;

    private Punter punter;
    private Kicker kicker;

    private Quarterback holder;

    private Center longSnapper;

    public TeamLayout(Quarterback quarterback, Quarterback quarterback2, Quarterback quarterback3, RunningBack runningBack, RunningBack runningBack2, RunningBack runningBack3, WideReceiver1 wideReceiver1, WideReceiver1 wideReceiver2, WideReceiver1 wideReceiver3, WideReceiver1 wideReceiver4, WideReceiver1 wideReceiver5, WideReceiver1 wideReceiver6, TightEnd tightEnd, TightEnd tightEnd2, Tackle1 tackle1, Tackle1 tackle2, Tackle1 tackle3, Tackle1 tackle4, Guard1 guard1, Guard1 guard2, Guard1 guard3, Guard1 guard4, Center center, Center center2, DefensiveTackle1 defensiveTackle1, DefensiveTackle1 defensiveTackle2, DefensiveTackle1 defensiveTackle3, DefensiveTackle1 defensiveTackle4, DefensiveTackle1 defensiveTackle5, DefensiveEnd1 defensiveEnd1, DefensiveEnd1 defensiveEnd2, DefensiveEnd1 defensiveEnd3, DefensiveEnd1 defensiveEnd4, DefensiveEnd1 defensiveEnd5, OutsideLinebacker1 outsideLinebacker1, OutsideLinebacker1 outsideLinebacker2, OutsideLinebacker1 outsideLinebacker3, OutsideLinebacker1 outsideLinebacker4, InsideLinebacker insideLinebacker, InsideLinebacker insideLinebacker2, InsideLinebacker insideLinebacker3, Safety1 safety1, Safety1 safety2, Safety1 safety3, Safety1 safety4, Cornerback1 cornerback1, Cornerback1 cornerback2, Cornerback1 cornerback3, Cornerback1 cornerback4, Punter punter, Kicker kicker, Quarterback holder, Center longSnapper)
    {
        this.quarterback = quarterback;
        this.quarterback2 = quarterback2;
        this.quarterback3 = quarterback3;
        this.runningBack = runningBack;
        this.runningBack2 = runningBack2;
        this.runningBack3 = runningBack3;
        this.wideReceiver1 = wideReceiver1;
        this.wideReceiver2 = wideReceiver2;
        this.wideReceiver3 = wideReceiver3;
        this.wideReceiver4 = wideReceiver4;
        this.wideReceiver5 = wideReceiver5;
        this.wideReceiver6 = wideReceiver6;
        this.tightEnd = tightEnd;
        this.tightEnd2 = tightEnd2;
        this.tackle1 = tackle1;
        this.tackle2 = tackle2;
        this.tackle3 = tackle3;
        this.tackle4 = tackle4;
        this.guard1 = guard1;
        this.guard2 = guard2;
        this.guard3 = guard3;
        this.guard4 = guard4;
        this.center = center;
        this.center2 = center2;
        this.defensiveTackle1 = defensiveTackle1;
        this.defensiveTackle2 = defensiveTackle2;
        this.defensiveTackle3 = defensiveTackle3;
        this.defensiveTackle4 = defensiveTackle4;
        this.defensiveTackle5 = defensiveTackle5;
        this.defensiveEnd1 = defensiveEnd1;
        this.defensiveEnd2 = defensiveEnd2;
        this.defensiveEnd3 = defensiveEnd3;
        this.defensiveEnd4 = defensiveEnd4;
        this.defensiveEnd5 = defensiveEnd5;
        this.outsideLinebacker1 = outsideLinebacker1;
        this.outsideLinebacker2 = outsideLinebacker2;
        this.outsideLinebacker3 = outsideLinebacker3;
        this.outsideLinebacker4 = outsideLinebacker4;
        this.insideLinebacker = insideLinebacker;
        this.insideLinebacker2 = insideLinebacker2;
        this.insideLinebacker3 = insideLinebacker3;
        this.safety1 = safety1;
        this.safety2 = safety2;
        this.safety3 = safety3;
        this.safety4 = safety4;
        this.cornerback1 = cornerback1;
        this.cornerback2 = cornerback2;
        this.cornerback3 = cornerback3;
        this.cornerback4 = cornerback4;
        this.punter = punter;
        this.kicker = kicker;
        this.holder = holder;
        this.longSnapper = longSnapper;
    }

    public Quarterback getQuarterback()
    {
        return quarterback;
    }

    public void setQuarterback(Quarterback quarterback)
    {
        this.quarterback = quarterback;
    }

    public Quarterback getQuarterback2()
    {
        return quarterback2;
    }

    public void setQuarterback2(Quarterback quarterback2)
    {
        this.quarterback2 = quarterback2;
    }

    public Quarterback getQuarterback3()
    {
        return quarterback3;
    }

    public void setQuarterback3(Quarterback quarterback3)
    {
        this.quarterback3 = quarterback3;
    }

    public RunningBack getRunningBack()
    {
        return runningBack;
    }

    public void setRunningBack(RunningBack runningBack)
    {
        this.runningBack = runningBack;
    }

    public RunningBack getRunningBack2()
    {
        return runningBack2;
    }

    public void setRunningBack2(RunningBack runningBack2)
    {
        this.runningBack2 = runningBack2;
    }

    public RunningBack getRunningBack3()
    {
        return runningBack3;
    }

    public void setRunningBack3(RunningBack runningBack3)
    {
        this.runningBack3 = runningBack3;
    }

    public WideReceiver1 getWideReceiver1()
    {
        return wideReceiver1;
    }

    public void setWideReceiver1(WideReceiver1 wideReceiver1)
    {
        this.wideReceiver1 = wideReceiver1;
    }

    public WideReceiver1 getWideReceiver2()
    {
        return wideReceiver2;
    }

    public void setWideReceiver2(WideReceiver1 wideReceiver2)
    {
        this.wideReceiver2 = wideReceiver2;
    }

    public WideReceiver1 getWideReceiver3()
    {
        return wideReceiver3;
    }

    public void setWideReceiver3(WideReceiver1 wideReceiver3)
    {
        this.wideReceiver3 = wideReceiver3;
    }

    public WideReceiver1 getWideReceiver4()
    {
        return wideReceiver4;
    }

    public void setWideReceiver4(WideReceiver1 wideReceiver4)
    {
        this.wideReceiver4 = wideReceiver4;
    }

    public WideReceiver1 getWideReceiver5()
    {
        return wideReceiver5;
    }

    public void setWideReceiver5(WideReceiver1 wideReceiver5)
    {
        this.wideReceiver5 = wideReceiver5;
    }

    public WideReceiver1 getWideReceiver6()
    {
        return wideReceiver6;
    }

    public void setWideReceiver6(WideReceiver1 wideReceiver6)
    {
        this.wideReceiver6 = wideReceiver6;
    }

    public TightEnd getTightEnd()
    {
        return tightEnd;
    }

    public void setTightEnd(TightEnd tightEnd)
    {
        this.tightEnd = tightEnd;
    }

    public TightEnd getTightEnd2()
    {
        return tightEnd2;
    }

    public void setTightEnd2(TightEnd tightEnd2)
    {
        this.tightEnd2 = tightEnd2;
    }

    public Tackle1 getTackle1()
    {
        return tackle1;
    }

    public void setTackle1(Tackle1 tackle1)
    {
        this.tackle1 = tackle1;
    }

    public Tackle1 getTackle2()
    {
        return tackle2;
    }

    public void setTackle2(Tackle1 tackle2)
    {
        this.tackle2 = tackle2;
    }

    public Tackle1 getTackle3()
    {
        return tackle3;
    }

    public void setTackle3(Tackle1 tackle3)
    {
        this.tackle3 = tackle3;
    }

    public Tackle1 getTackle4()
    {
        return tackle4;
    }

    public void setTackle4(Tackle1 tackle4)
    {
        this.tackle4 = tackle4;
    }

    public Guard1 getGuard1()
    {
        return guard1;
    }

    public void setGuard1(Guard1 guard1)
    {
        this.guard1 = guard1;
    }

    public Guard1 getGuard2()
    {
        return guard2;
    }

    public void setGuard2(Guard1 guard2)
    {
        this.guard2 = guard2;
    }

    public Guard1 getGuard3()
    {
        return guard3;
    }

    public void setGuard3(Guard1 guard3)
    {
        this.guard3 = guard3;
    }

    public Guard1 getGuard4()
    {
        return guard4;
    }

    public void setGuard4(Guard1 guard4)
    {
        this.guard4 = guard4;
    }

    public Center getCenter()
    {
        return center;
    }

    public void setCenter(Center center)
    {
        this.center = center;
    }

    public Center getCenter2()
    {
        return center2;
    }

    public void setCenter2(Center center2)
    {
        this.center2 = center2;
    }

    public DefensiveTackle1 getDefensiveTackle1()
    {
        return defensiveTackle1;
    }

    public void setDefensiveTackle1(DefensiveTackle1 defensiveTackle1)
    {
        this.defensiveTackle1 = defensiveTackle1;
    }

    public DefensiveTackle1 getDefensiveTackle2()
    {
        return defensiveTackle2;
    }

    public void setDefensiveTackle2(DefensiveTackle1 defensiveTackle2)
    {
        this.defensiveTackle2 = defensiveTackle2;
    }

    public DefensiveTackle1 getDefensiveTackle3()
    {
        return defensiveTackle3;
    }

    public void setDefensiveTackle3(DefensiveTackle1 defensiveTackle3)
    {
        this.defensiveTackle3 = defensiveTackle3;
    }

    public DefensiveTackle1 getDefensiveTackle4()
    {
        return defensiveTackle4;
    }

    public void setDefensiveTackle4(DefensiveTackle1 defensiveTackle4)
    {
        this.defensiveTackle4 = defensiveTackle4;
    }

    public DefensiveTackle1 getDefensiveTackle5()
    {
        return defensiveTackle5;
    }

    public void setDefensiveTackle5(DefensiveTackle1 defensiveTackle5)
    {
        this.defensiveTackle5 = defensiveTackle5;
    }

    public DefensiveEnd1 getDefensiveEnd1()
    {
        return defensiveEnd1;
    }

    public void setDefensiveEnd1(DefensiveEnd1 defensiveEnd1)
    {
        this.defensiveEnd1 = defensiveEnd1;
    }

    public DefensiveEnd1 getDefensiveEnd2()
    {
        return defensiveEnd2;
    }

    public void setDefensiveEnd2(DefensiveEnd1 defensiveEnd2)
    {
        this.defensiveEnd2 = defensiveEnd2;
    }

    public DefensiveEnd1 getDefensiveEnd3()
    {
        return defensiveEnd3;
    }

    public void setDefensiveEnd3(DefensiveEnd1 defensiveEnd3)
    {
        this.defensiveEnd3 = defensiveEnd3;
    }

    public DefensiveEnd1 getDefensiveEnd4()
    {
        return defensiveEnd4;
    }

    public void setDefensiveEnd4(DefensiveEnd1 defensiveEnd4)
    {
        this.defensiveEnd4 = defensiveEnd4;
    }

    public DefensiveEnd1 getDefensiveEnd5()
    {
        return defensiveEnd5;
    }

    public void setDefensiveEnd5(DefensiveEnd1 defensiveEnd5)
    {
        this.defensiveEnd5 = defensiveEnd5;
    }

    public OutsideLinebacker1 getOutsideLinebacker1()
    {
        return outsideLinebacker1;
    }

    public void setOutsideLinebacker1(OutsideLinebacker1 outsideLinebacker1)
    {
        this.outsideLinebacker1 = outsideLinebacker1;
    }

    public OutsideLinebacker1 getOutsideLinebacker2()
    {
        return outsideLinebacker2;
    }

    public void setOutsideLinebacker2(OutsideLinebacker1 outsideLinebacker2)
    {
        this.outsideLinebacker2 = outsideLinebacker2;
    }

    public OutsideLinebacker1 getOutsideLinebacker3()
    {
        return outsideLinebacker3;
    }

    public void setOutsideLinebacker3(OutsideLinebacker1 outsideLinebacker3)
    {
        this.outsideLinebacker3 = outsideLinebacker3;
    }

    public OutsideLinebacker1 getOutsideLinebacker4()
    {
        return outsideLinebacker4;
    }

    public void setOutsideLinebacker4(OutsideLinebacker1 outsideLinebacker4)
    {
        this.outsideLinebacker4 = outsideLinebacker4;
    }

    public InsideLinebacker getInsideLinebacker()
    {
        return insideLinebacker;
    }

    public void setInsideLinebacker(InsideLinebacker insideLinebacker)
    {
        this.insideLinebacker = insideLinebacker;
    }

    public InsideLinebacker getInsideLinebacker2()
    {
        return insideLinebacker2;
    }

    public void setInsideLinebacker2(InsideLinebacker insideLinebacker2)
    {
        this.insideLinebacker2 = insideLinebacker2;
    }

    public InsideLinebacker getInsideLinebacker3()
    {
        return insideLinebacker3;
    }

    public void setInsideLinebacker3(InsideLinebacker insideLinebacker3)
    {
        this.insideLinebacker3 = insideLinebacker3;
    }

    public Safety1 getSafety1()
    {
        return safety1;
    }

    public void setSafety1(Safety1 safety1)
    {
        this.safety1 = safety1;
    }

    public Safety1 getSafety2()
    {
        return safety2;
    }

    public void setSafety2(Safety1 safety2)
    {
        this.safety2 = safety2;
    }

    public Safety1 getSafety3()
    {
        return safety3;
    }

    public void setSafety3(Safety1 safety3)
    {
        this.safety3 = safety3;
    }

    public Safety1 getSafety4()
    {
        return safety4;
    }

    public void setSafety4(Safety1 safety4)
    {
        this.safety4 = safety4;
    }

    public Cornerback1 getCornerback1()
    {
        return cornerback1;
    }

    public void setCornerback1(Cornerback1 cornerback1)
    {
        this.cornerback1 = cornerback1;
    }

    public Cornerback1 getCornerback2()
    {
        return cornerback2;
    }

    public void setCornerback2(Cornerback1 cornerback2)
    {
        this.cornerback2 = cornerback2;
    }

    public Cornerback1 getCornerback3()
    {
        return cornerback3;
    }

    public void setCornerback3(Cornerback1 cornerback3)
    {
        this.cornerback3 = cornerback3;
    }

    public Cornerback1 getCornerback4()
    {
        return cornerback4;
    }

    public void setCornerback4(Cornerback1 cornerback4)
    {
        this.cornerback4 = cornerback4;
    }

    public Punter getPunter()
    {
        return punter;
    }

    public void setPunter(Punter punter)
    {
        this.punter = punter;
    }

    public Kicker getKicker()
    {
        return kicker;
    }

    public void setKicker(Kicker kicker)
    {
        this.kicker = kicker;
    }

    public Quarterback getHolder()
    {
        return holder;
    }

    public void setHolder(Quarterback holder)
    {
        this.holder = holder;
    }

    public Center getLongSnapper()
    {
        return longSnapper;
    }

    public void setLongSnapper(Center longSnapper)
    {
        this.longSnapper = longSnapper;
    }
}
