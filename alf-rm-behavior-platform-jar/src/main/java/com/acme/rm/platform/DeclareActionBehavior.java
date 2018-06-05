package com.acme.rm.platform;

import org.alfresco.module.org_alfresco_module_rm.dod5015.DOD5015Model;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeclareActionBehavior implements NodeServicePolicies.OnAddAspectPolicy {

	private static Logger logger = LoggerFactory.getLogger(DeclareActionBehavior.class);

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
				NodeServicePolicies.OnAddAspectPolicy.QNAME,
				DOD5015Model.ASPECT_DOD_5015_RECORD, // dod:dod5015record aspect, this only works for DoD RM site
				new JavaBehaviour(this, "onAddAspect", NotificationFrequency.TRANSACTION_COMMIT));
	}

	@Override
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {
		logger.info("nodeRef={}, aspect={}", nodeRef.toString(), aspectTypeQName.getPrefixString());
	}

}
