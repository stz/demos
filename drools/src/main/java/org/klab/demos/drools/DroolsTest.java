package org.klab.demos.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {
     public static final void main(String[] args) {
          try {
               // load up the knowledge base
               KnowledgeBase kbase = readKnowledgeBase();
               StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
               KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "log");
               
               // go !
               Vote vote = new Vote();
               vote.setAverage(8.4f);
               
               //insert the "vote"
               ksession.insert(vote);
               
               //"fire all rules"
               ksession.fireAllRules();
               
               //clean up
               logger.close();
               ksession.dispose();
               
          } catch (Throwable t) {
               t.printStackTrace();
          }
     }
 
     private static KnowledgeBase readKnowledgeBase() throws Exception {
          KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
          kbuilder.add(ResourceFactory.newClassPathResource("org/klab/demos/drools/vote.drl"), ResourceType.DRL);
          KnowledgeBuilderErrors errors = kbuilder.getErrors();
          if (errors.size() > 0) {
               for (KnowledgeBuilderError error: errors) {
                    System.err.println(error);
               }
               throw new IllegalArgumentException("Could not parse knowledge.");
          }
          KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
          kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
          return kbase;
     }
 
     public static class Vote {
          
         private String vote;
          private float average;
 
          public String getVote() {
               return this.vote;
          }
 
          public void setVote(String vote) {
               this.vote = vote;
          }
 
          public float getAverage() {
               return this.average;
          }
 
          public void setAverage(float average) {
               this.average = average;
          }
          
     }
}