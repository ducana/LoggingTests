package de.fraunhofer.iosb.tc;

import de.fraunhofer.iosb.tc_lib.IVCT_RTI;
import de.fraunhofer.iosb.tc_lib.IVCT_RTI_Factory;
import de.fraunhofer.iosb.tc_lib.LocalCacheTc;
import de.fraunhofer.iosb.tc_lib.LocalCacheTcFactory;
import de.fraunhofer.iosb.tc_lib.TcParam;
import hla.rti1516e.CallbackModel;
import hla.rti1516e.ResignAction;
import hla.rti1516e.exceptions.CallNotAllowedFromWithinCallback;
import hla.rti1516e.exceptions.CouldNotCreateLogicalTimeFactory;
import hla.rti1516e.exceptions.CouldNotOpenFDD;
import hla.rti1516e.exceptions.ErrorReadingFDD;
import hla.rti1516e.exceptions.FederateAlreadyExecutionMember;
import hla.rti1516e.exceptions.FederateIsExecutionMember;
import hla.rti1516e.exceptions.FederateNotExecutionMember;
import hla.rti1516e.exceptions.FederateOwnsAttributes;
import hla.rti1516e.exceptions.FederatesCurrentlyJoined;
import hla.rti1516e.exceptions.FederationExecutionAlreadyExists;
import hla.rti1516e.exceptions.FederationExecutionDoesNotExist;
import hla.rti1516e.exceptions.InconsistentFDD;
import hla.rti1516e.exceptions.InvalidResignAction;
import hla.rti1516e.exceptions.NotConnected;
import hla.rti1516e.exceptions.OwnershipAcquisitionPending;
import hla.rti1516e.exceptions.RTIinternalError;
import hla.rti1516e.exceptions.RestoreInProgress;
import hla.rti1516e.exceptions.SaveInProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TC00001 {
    // Test case parameters
    private static Logger                    logger              = LoggerFactory.getLogger(TC00001.class);
    private static final LocalCacheTcFactory localCacheTcFactory = new LocalCacheTcFactory();


    public static void main(final String[] args) {
        // Build test case parameters to use
        logger.info("TEST CASE MAIN");
        final TcParam tcParam = new TcParam();
        execute(tcParam);
    }


    /**
     * @param tcParam test case parameters
     */
    public static void execute(final TcParam tcParam) {
        // Get logging-IVCT-RTI using tc_param federation name, host
        final IVCT_RTI ivct_rti = IVCT_RTI_Factory.getIVCT_RTI(logger);
        final LocalCacheTc localCacheTc = (LocalCacheTc) localCacheTcFactory.getLocalCache(ivct_rti, logger, tcParam);

        // Test case phase
        logger.info("TEST CASE PREAMBLE");
        localCacheTc.connect(localCacheTc, CallbackModel.HLA_IMMEDIATE, tcParam.getSettingsDesignator());

        // Create federation using tc_param foms
        try {
            ivct_rti.createFederationExecution(tcParam.getFederationName(), tcParam.getUrls(), "HLAfloat64Time");
        }
        catch (CouldNotCreateLogicalTimeFactory | InconsistentFDD | ErrorReadingFDD | CouldNotOpenFDD | FederationExecutionAlreadyExists | NotConnected | RTIinternalError e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        // Join federation
        try {
            ivct_rti.joinFederationExecution("TestCase", tcParam.getFederationName(), tcParam.getUrls());
        }
        catch (CouldNotCreateLogicalTimeFactory | FederationExecutionDoesNotExist | InconsistentFDD | ErrorReadingFDD | CouldNotOpenFDD | SaveInProgress | RestoreInProgress | FederateAlreadyExecutionMember | NotConnected | CallNotAllowedFromWithinCallback | RTIinternalError e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        // Publish interaction / object classes

        // Subscribe interaction / object classes

        // Test case phase
        logger.info("TEST CASE BODY");

        // PERFORM TEST

        // Test case phase
        logger.info("TEST CASE POST-AMBLE");

        // Resign federation
        try {
            ivct_rti.resignFederationExecution(ResignAction.DELETE_OBJECTS_THEN_DIVEST);
        }
        catch (InvalidResignAction | OwnershipAcquisitionPending | FederateOwnsAttributes | FederateNotExecutionMember | NotConnected | CallNotAllowedFromWithinCallback | RTIinternalError e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        // Destroy federation
        try {
            ivct_rti.destroyFederationExecution(tcParam.getFederationName());
        }
        catch (FederatesCurrentlyJoined | FederationExecutionDoesNotExist | NotConnected | RTIinternalError e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Disconnect
        try {
            ivct_rti.disconnect();
        }
        catch (FederateIsExecutionMember | CallNotAllowedFromWithinCallback | RTIinternalError e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
