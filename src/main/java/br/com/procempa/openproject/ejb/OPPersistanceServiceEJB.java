package br.com.procempa.openproject.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.procempa.openproject.integration.entity.RepositoryManager;
import br.com.procempa.openproject.integration.entity.TimeEntry;
import br.com.procempa.openproject.integration.entity.TotalTime;
import br.com.procempa.openproject.integration.entity.Users;
import br.com.procempa.openproject.integration.entity.WorkPackages;

@Stateless
public class OPPersistanceServiceEJB {
	@Inject
	RepositoryManager repositoryManager;

	static final int LANCAMENTO_DE_HORAS = 45;
	public List<WorkPackages> getWorkPackages(String userId){
		return repositoryManager.getWorkPackages(userId);
	}
	
	public Users getUsersInfoFromSecurityToken(String securityToken){
		return repositoryManager.getUserInfoFromSecurityToken(securityToken);
	}

	public String geEmailFromSecurityToken(String token){
		return repositoryManager.getUserEmailFronSecurityToken(token);
	}

	public void updateWorkpackageStatus(int workPackageId, int statusId){
		repositoryManager.updateWorkPackageStatus(workPackageId, statusId);
	}

	public List<TotalTime> geTotalNumberHours(int userId){
		return repositoryManager.getTotalNumberOfHours(userId);
	}

	public void addTimeEntry(int userId, int packageId, int projectId, float time){
		 
		 TimeEntry timeEntry = new TimeEntry();
		 timeEntry.setWorkPackageId(packageId);
		 timeEntry.setActivityId(LANCAMENTO_DE_HORAS);
		 timeEntry.setCreatedOn(new Date());
		 timeEntry.setHours(time);
		 timeEntry.setProjectId(projectId);
		 timeEntry.setRateId(0);
		 timeEntry.setUserId(userId);
		 timeEntry.setWorkPackageId(packageId);
		 timeEntry.setSpentOn(new Date());
		 timeEntry.setUpdatedOn(new Date());
		 timeEntry.setComments("Adicionado automaticamente pelo aplicativo móvel OPTimeTracker");
		 
		 repositoryManager.addTimeEntry(timeEntry);
	 }
	
}
