/**
 * JanEvent.java
 */

package wiz.project.jan.event;

import java.util.EventObject;

import wiz.project.jan.JanPai;
import wiz.project.jan.Wind;



/**
 * 麻雀イベント
 */
public final class JanEvent extends EventObject {
    
    /**
     * コンストラクタ
     * 
     * @param param イベントパラメータ。
     */
    public JanEvent(final Event param) {
        super(param);
    }
    
    /**
     * コンストラクタ
     * 
     * @param param イベントパラメータ。
     * @param wind 風。
     */
    public JanEvent(final Event param, final Wind wind) {
        this(param);
        
        if (wind != null) {
            _wind = wind;
        }
    }
    
    /**
     * コンストラクタ
     * 
     * @param param イベントパラメータ。
     * @param pai 牌。
     */
    public JanEvent(final Event param, final JanPai pai) {
        this(param);
        
        if (pai != null) {
            _pai = pai;
        }
    }
    
    /**
     * コンストラクタ
     * 
     * @param param イベントパラメータ。
     * @param wind 風。
     * @param pai 牌。
     */
    public JanEvent(final Event param, final Wind wind, final JanPai pai) {
        this(param, wind);
        
        if (pai != null) {
            _pai = pai;
        }
    }
    
    
    
    /**
     * 牌を取得
     * 
     * @return 牌。
     */
    public JanPai getJanPai() {
        return _pai;
    }
    
    /**
     * イベントの種類を取得
     */
    @Override
    public Event getSource() {
        if (source instanceof Event) {
            return (Event)source;
        }
        else {
            return Event.BLANK;
        }
    }
    
    /**
     * 風を取得
     * 
     * @return 風。
     */
    public Wind getWind() {
        return _wind;
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * 風
     */
    private Wind _wind = Wind.TON;
    
    /**
     * 牌
     */
    private JanPai _pai = JanPai.HAKU;
    
}

