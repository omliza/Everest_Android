package com.sxm.mobile.pages.factory;

//import com.sxm.mobile.tests.VedioTest;
import com.sxm.mobile.everest.pages.CategoryPage;
import com.sxm.mobile.everest.pages.DMCAPage;
import com.sxm.mobile.everest.pages.EverestHome;
import com.sxm.mobile.everest.pages.FavoritesPage;
import com.sxm.mobile.everest.pages.RecentPage;
import com.sxm.mobile.everest.pages.Home;
import com.sxm.mobile.everest.pages.HowardStern;
import com.sxm.mobile.everest.pages.NowPlayingPage;
import com.sxm.mobile.everest.pages.ProfilePage;
import com.sxm.mobile.everest.pages.SearchPage;
import com.sxm.mobile.everest.pages.VedioPage;
import com.sxm.mobile.pages.Login;
import com.sxm.mobile.pages.NetworkVirtualizationUtil;
//import com.sxm.mobile.tests.NowPlayingTest;

import io.appium.java_client.AppiumDriver;

public class SXMMobilePageFactory {

	private AppiumDriver driver;

	public SXMMobilePageFactory(AppiumDriver driver) {
		this.driver = driver;
		evehome = new EverestHome(driver);
		login = new Login(driver);
		howardStern=new HowardStern(driver);
		dmca=new DMCAPage(driver);
		favorites= new FavoritesPage(driver);
		search=new SearchPage(driver); 
		networkVirtualizationUtil = new NetworkVirtualizationUtil(driver);
		home =new Home(driver);
		category = new CategoryPage(driver);
		profile = new ProfilePage(driver);
		nowplayingpage =  new NowPlayingPage(driver);
		vediopage = new VedioPage(driver);
		recentPage = new RecentPage(driver);
//		eveminibar =new MiniBar(driver);
	}
	
	private EverestHome evehome;
	private Login login;// = new Login(driver);
	private HowardStern howardStern;
	private DMCAPage dmca;
	private FavoritesPage favorites;
	private SearchPage search;
	private NetworkVirtualizationUtil networkVirtualizationUtil;
	private Home home;
	private CategoryPage category;
	private ProfilePage profile;
	private NowPlayingPage nowplayingpage;
	private VedioPage vediopage;
	private RecentPage recentPage;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
	public EverestHome getEvehome() {
		return evehome;
	}

	public void setEvehome(EverestHome evehome) {
		this.evehome = evehome;
	}
	
	/*public MiniBar getMinibar(){
		return eveminibar;
	}
	
	public void setMinibar(MiniBar eveminibar ){
		this.eveminibar = eveminibar;
	}*/
	
	public HowardStern getHoward(){
		return howardStern;
	}
	
	public void setHoward(HowardStern HowardStern){
		this.howardStern = HowardStern;
	}
	
	public DMCAPage getdmca(){
		return dmca;
	}
	
	public void setdmca(DMCAPage Dmca){
		this.dmca= Dmca;
	}
	
	
	
	public FavoritesPage getFavorites() {
		return favorites;
	}

	public void setFavorites(FavoritesPage favorites) {
		this.favorites = favorites;
	}

	/*
	 * Get Recent Page - Recent tab in tabbar
	 */
	public RecentPage getRecentPage() {
		return recentPage;
	}
	
	/*
	 * Set Recent page
	 */
	public void setRecentPage (RecentPage recentPage)
	{
		this.recentPage = recentPage;
	}
	
	public SearchPage getSearch(){
		return search;
	}
	
	public void setSearch(SearchPage search){
		this.search=search;
	}

	/**
	 * @return the networkVirtualizationUtil
	 */
	public NetworkVirtualizationUtil getNetworkVirtualizationUtil() {
		return networkVirtualizationUtil;
	}

	/**
	 * @param networkVirtualizationUtil the networkVirtualizationUtil to set
	 */
	public void setNetworkVirtualizationUtil(NetworkVirtualizationUtil networkVirtualizationUtil) {
		this.networkVirtualizationUtil = networkVirtualizationUtil;
	}
	
	public Home getHome() {
		return home;
	}
	
	public void setHome(Home home) {
		this.home = home;
	}

	public CategoryPage getCategory() {
		return category;
	}

	public void setCategory(CategoryPage category) {
		this.category = category;
	}
	

	public ProfilePage getProfile() {
		return profile;
	}

	public void setProfile(ProfilePage profile) {
		this.profile = profile;
	}
	



	/**
	 * @return the nowplayingpage
	 */
	public NowPlayingPage getNowplayingpage() {
		return nowplayingpage;
	}

	/**
	 * @param nowplayingpage the nowplayingpage to set
	 */
	public void setNowplayingpage(NowPlayingPage nowplayingpage) {
		this.nowplayingpage = nowplayingpage;
	} 
	
	/**
	 * @return the vediopage
	 */
	public VedioPage getVediopage() {
		return vediopage;
	}

	/**
	 * @param vediopage the vediopage to set
	 */
	public void setVediopage(VedioPage vediopage) {
		this.vediopage = vediopage;
	}

}
