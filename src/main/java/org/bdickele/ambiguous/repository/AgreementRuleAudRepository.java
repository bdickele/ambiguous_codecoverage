package org.bdickele.ambiguous.repository;

import org.bdickele.ambiguous.domain.audit.AgreementRuleAud;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bertrand DICKELE
 */
public interface AgreementRuleAudRepository extends JpaRepository<AgreementRuleAud, Long> {

    AgreementRuleAud findTopByDestinationIdAndGoodsIdOrderByPkVersionDesc(Long destinationId, Long goodsId);
}
