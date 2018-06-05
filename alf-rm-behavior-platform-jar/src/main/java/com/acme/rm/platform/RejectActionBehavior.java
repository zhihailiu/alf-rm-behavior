package com.acme.rm.platform;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.module.org_alfresco_module_rm.RecordsManagementPolicies;
import org.alfresco.module.org_alfresco_module_rm.action.impl.RejectAction;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.NodeRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RejectActionBehavior implements RecordsManagementPolicies.OnRMActionExecution {

	private static Logger logger = LoggerFactory.getLogger(RejectActionBehavior.class);

	private PolicyComponent policyComponent;
	
	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}
	
	
	/**
	 * Initialize/register.
	 */
	public void init() {
		logger.info("init");
        this.policyComponent.bindClassBehaviour(
                RecordsManagementPolicies.ON_RM_ACTION_EXECUTION,
                ContentModel.TYPE_CONTENT, // cm:content type
                new JavaBehaviour(this, "onRMActionExecution", NotificationFrequency.TRANSACTION_COMMIT));
        // NotificationFrequency.EVERY_EVENT works too
	}

	@Override
	public void onRMActionExecution(NodeRef nodeRef, String name, Map<String, Serializable> parameters) {
		logger.info("nodeRef={}, name={}", nodeRef.toString(), name);
		if (RejectAction.NAME.equals(name)) {
			logger.info("Reject record");
		}
	}

}
