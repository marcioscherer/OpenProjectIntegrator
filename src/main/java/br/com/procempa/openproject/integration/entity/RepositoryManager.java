package br.com.procempa.openproject.integration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RepositoryManager {

	@Inject
	private EntityManager em;

	 public void addTimeEntry(TimeEntry timeEntry){
	   em.persist(timeEntry);
	 }

	@SuppressWarnings("unchecked")
	public List<WorkPackages> getWorkPackages(String user_id) {
		Query q = em
				.createNativeQuery(
						"SELECT w.id, w.type_id, w.rgt, w.updated_at, w.story_points, w.root_id, w.start_date, w.responsible_id, "
						+ "w.remaining_hours, w.position, w.parent_id, w.lock_version, w.project_id, w.lft, w.fixed_version_id, "
						+ "w.estimated_hours, w.subject, w.created_at, w.done_ratio, w.due_date, w.cost_object_id, w.category_id, "
						+ "w.author_id, w.status_id, w.assigned_to_id, w.priority_id FROM openproject.work_packages w, "
						+ "openproject.statuses s where "
						+ "s.id=w.status_id and s.name not in('Confirmado','Suspenso', 'Cancelado', 'Concluído', 'Resolvido') "
						+ "and assigned_to_id="+user_id,
						WorkPackages.class);
		
		List<WorkPackages> workPackages = q.getResultList();

		for (WorkPackages w : workPackages) {
			System.out.println("Package " + w.getSubject());
		}

		return workPackages;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserEmailFronSecurityToken(String securityToken) {
		Query q = em
				.createNativeQuery(
						"select u.mail from openproject.tokens t, openproject.users u where "
						+ "t.value='"+securityToken+"' and t.user_id=u.id");

		String email = (String) q.getSingleResult();
		
		return email;
	}
	
	@SuppressWarnings("unchecked")
	public Users getUserInfoFromSecurityToken(String securityToken) {
		Query q = em
				.createNativeQuery(
						"SELECT u.id, u.mail, u.login FROM openproject.users u, openproject.tokens t WHERE " 
						+ "t.value='"+securityToken+"' and t.user_id=u.id");

		List data = q.getResultList();
		Users user = new Users();
		Object[] rawData = ((Object[])data.get(0));
		
		user.setId(new Integer(String.valueOf(rawData[0])));
		user.setMail((String)rawData[1]);
		user.setLogin((String)rawData[2]);
		
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<TotalTime> getTotalNumberOfHours(int userId) {
		List<TotalTime> times = new ArrayList<TotalTime>();
		
		Query q = em
				.createNativeQuery(
						"select cast(work_package_id as char), cast(sum(hours)  as char) as total from openproject.time_entries where user_id="+userId
								+ " group by work_package_id");

		 List<Object[]> results = q.getResultList();
		  for (Object[] result : results) {
		      times.add(new TotalTime(String.valueOf(result[0]), String.valueOf(result[1])));
		  }		
		  
		  return times;	
	}
	
	@SuppressWarnings("unchecked")
	public void updateWorkPackageStatus(int workpackageId, int statusId) {
		Query q = em
				.createNativeQuery(
						"update openproject.work_packages set status_id="+statusId+" where id="+workpackageId);
		q.executeUpdate();
	}

}