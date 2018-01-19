package com.sxm.mobile.common;

import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import com.testdroid.api.DefaultAPIClient;
import com.testdroid.api.model.APIDeviceRun;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APITestRun;
import com.testdroid.api.model.APIUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Samuli Peurasaari on 11/3/15.
 */
public class TestdroidDeviceRunService {

    public static final String TESTDROID_CLOUD_URL = "https://cloud.testdroid.com";

    private String username;
    private String password;
    private String projectName;

    public TestdroidDeviceRunService(String testdroidUsername, String testdroidPassword, String testdroidProjectName) {
        this.username = testdroidUsername;
        this.password = testdroidPassword;
        this.projectName = testdroidProjectName;
    }

    public int getDeviceRunIndex() throws Exception {
        System.out.println("Get device run index from cloud");
        Map<String, String> env = System.getenv();
        if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0)
            throw new Exception("TESTDROID_USERNAME and/or TESTDROID_PASSWORD not set!");
        String runId = env.get("TESTDROID_RUN_ID");
       /// runId = "84716517";
        if (runId == null || runId.trim().length() == 0) throw new Exception("No TESTDROID_RUN_ID in environment");

        DefaultAPIClient api = new DefaultAPIClient(TESTDROID_CLOUD_URL, username, password);
        APIUser me = api.me();
        APIProject project = getProject(me);
        if (project == null) throw new Exception("Project not found or name not set!");

        APIListResource<APITestRun> testRunResource = project.getTestRunsResource();
        List<APITestRun> testRuns = testRunResource.getEntity().getData();
        for (APITestRun testRun : testRuns) {
            APIListResource<APIDeviceRun> deviceRunsResource = testRun.getDeviceRunsResource();
            List<APIDeviceRun> deviceRunList = deviceRunsResource.getEntity().getData();
            int index = 0;
            for (APIDeviceRun deviceRun : deviceRunList) {
                if (runId.trim().equals(deviceRun.getId().toString())) {
                    // System.out.println("Found device run for id " + runId + " with index " + index);
                    return index;
                }
                index++;
            }
        }
        throw new Exception("No device run for id " + runId + " found!");
    }

    private APIProject getProject(APIUser me) throws APIException {
        APIListResource<APIProject> projectsResource = me.getProjectsResource(new APIQueryBuilder().offset(0).limit(10).search(projectName));
        List<APIProject> projects = projectsResource.getEntity().getData();
        if (projects.size() > 0) {
            return projects.get(0);
        } else {
            return null;
        }
    }
    
    public static void main(String[] args){
        try {
            int index = new TestdroidDeviceRunService("Subramanyam.Palivela@siriusxm.com", "AutoTest@SiriusXM1", "SXMChannelsAndroid").getDeviceRunIndex();
            System.out.println(index);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
