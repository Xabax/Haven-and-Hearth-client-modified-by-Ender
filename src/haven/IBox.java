package haven;

public class IBox {
	Tex ctl, ctr, cbl, cbr;
	Tex bl, br, bt, bb;
	
	public IBox(Tex ctl, Tex ctr, Tex cbl, Tex cbr, Tex bl, Tex br, Tex bt, Tex bb) {
		this.ctl = ctl;
		this.ctr = ctr;
		this.cbl = cbl;
		this.cbr = cbr;
		this.bl = bl;
		this.br = br;
		this.bt = bt;
		this.bb = bb;
	}
	
	public IBox(String base, String ctl, String ctr, String cbl, String cbr, String bl, String br, String bt, String bb) {
		this(Resource.loadtex(base + "/" + ctl),
		     Resource.loadtex(base + "/" + ctr),
		     Resource.loadtex(base + "/" + cbl),
		     Resource.loadtex(base + "/" + cbr),
		     Resource.loadtex(base + "/" + bl),
		     Resource.loadtex(base + "/" + br),
		     Resource.loadtex(base + "/" + bt),
		     Resource.loadtex(base + "/" + bb));
	}
	
	public Coord tloff() {
		return(new Coord(bl.sz().x, bt.sz().y));
	}
	
	public Coord ctloff() {
		return(ctl.sz());
	}
	
	public Coord bisz() {
		return(new Coord(bl.sz().x + br.sz().x, bt.sz().y + bb.sz().y));
	}
	
	public Coord bsz() {
		return(ctl.sz().add(cbr.sz()));
	}
	
	public void draw(GOut g, Coord tl, Coord sz) {
		for(int x = ctl.sz().x; x < sz.x - ctr.sz().x; x++)
			g.image(bt, tl.add(x, 0));
		for(int x = cbl.sz().x; x < sz.x - cbr.sz().x; x++)
			g.image(bb, new Coord(x + tl.x, sz.y - bb.sz().y + tl.y));
		for(int y = ctl.sz().y; y < sz.y - cbl.sz().y; y++)
			g.image(bl, tl.add(0, y));
		for(int y = ctr.sz().y; y < sz.y - cbr.sz().y; y++)
			g.image(br, new Coord(sz.x - br.sz().x + tl.x, y + tl.y));
		g.image(ctl, tl);
		g.image(ctr, tl.add(sz.x - ctr.sz().x, 0));
		g.image(cbl, tl.add(0, sz.y - cbl.sz().y));
		g.image(cbr, new Coord(sz.x - cbr.sz().x + tl.x, sz.y - cbr.sz().y + tl.y));
	}
}
