package org.tygus.suslik.synthesis.instances

import org.tygus.suslik.logic.Specifications.Goal
import org.tygus.suslik.synthesis._
import org.tygus.suslik.synthesis.rules._
import org.tygus.suslik.synthesis.rules.Rules.SynthesisRule
import org.tygus.suslik.util.SynLogging

/**
  * @author Ilya Sergey
  */

class SimpleSynthesis(implicit val log: SynLogging) extends Synthesis {

  val startingDepth = 27

  def allRules(goal: Goal): List[SynthesisRule] = List(
    // Terminal
    LogicalRules.EmpRule,

    // Normalization rules
    LogicalRules.StarPartial,
    LogicalRules.NilNotLval,
    LogicalRules.SubstLeft,
    LogicalRules.Inconsistency,
    UnificationRules.SubstRight,

    OperationalRules.ReadRule,
    UnfoldingRules.Open,

    // Subtraction rules
    UnificationRules.StarIntro,

    // Invertible operational rules
    OperationalRules.WriteRule,

    // If these come last, it goes to an eternal alloc/free spiral. :(
    //    UnfoldingRules.AbductWritesAndCallRule,
    UnfoldingRules.CallRule,
    UnfoldingRules.AbduceCall,

    UnfoldingRules.Close,

    // Noninvertible operational rules
    // OperationalRules.WriteRuleOld,
    OperationalRules.AllocRule,
    OperationalRules.FreeRule,

    UnificationRules.PureUnify,
    UnificationRules.Pick,
    UnificationRules.PickFromEnvRule,

  )

  def nextRules(goal: Goal, depth: Int): List[SynthesisRule] = allRules(goal)

}
